package shop.mtcoding.kimhyungjoon2.controller;

import shop.mtcoding.kimhyungjoon2.core.exception.Exception400;
import shop.mtcoding.kimhyungjoon2.core.exception.Exception401;
import shop.mtcoding.kimhyungjoon2.core.jwt.JwtProvider;
import shop.mtcoding.kimhyungjoon2.dto.ResponseDto;
import shop.mtcoding.kimhyungjoon2.dto.user.UserRequest;
import shop.mtcoding.kimhyungjoon2.model.log.login.LoginLog;
import shop.mtcoding.kimhyungjoon2.model.log.login.LoginLogRepository;
import shop.mtcoding.kimhyungjoon2.model.user.User;
import shop.mtcoding.kimhyungjoon2.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final LoginLogRepository loginLogRepository;
    private final HttpSession session;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest.LoginDto loginDto, HttpServletRequest request){
        Optional<User> userOp = userRepository.findByUsername(loginDto.getUsername());
        if(userOp.isPresent()){ //Optional객체를 사용하고 한번더 확인해야된다.
            // 1. 유저 정보 꺼내기
            User loginUser = userOp.get();

            // 2. 패스워드 검증하기
            if(!loginUser.getPassword().equals(loginDto.getPassword())){
                throw new Exception401("인증되지 않았습니다.");
            }
            // 3. JWT 생성하기
            String jwt = JwtProvider.create(userOp.get());
            // 4. 최종 로그인 날짜 기록 (더티체킹 - update 쿼리 발생)
            loginUser.setUpdatedAt(LocalDateTime.now());// 로그인-토큰생성-업데이트쿼리(초기 동의 검증아님)
            // 5. 로그 테이블 기록
            LoginLog loginLog = LoginLog.builder()
                    .userId(loginUser.getId())
                    .userAgent(request.getHeader("User-Agent"))
                    .clientIP(request.getRemoteAddr())
                    .build();
            loginLogRepository.save(loginLog);

            // 6. 응답 DTO 생성
            ResponseDto<?> responseDto = new ResponseDto<>().data(loginUser); //body에 loginUser넣기
            return ResponseEntity.ok().header(JwtProvider.HEADER,jwt).body(responseDto);
        } else{
            throw new Exception400("유저네임 혹은 아이디가 잘못되었습니다.");
        }
    }


}
