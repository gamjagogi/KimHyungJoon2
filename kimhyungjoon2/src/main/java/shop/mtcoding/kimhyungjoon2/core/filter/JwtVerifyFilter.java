package shop.mtcoding.kimhyungjoon2.core.filter;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import shop.mtcoding.kimhyungjoon2.core.exception.Exception400;
import shop.mtcoding.kimhyungjoon2.core.jwt.JwtProvider;
import shop.mtcoding.kimhyungjoon2.core.session.LoginUser;
import shop.mtcoding.kimhyungjoon2.dto.ResponseDto;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class JwtVerifyFilter implements Filter {
    //jwt filter + login후 session에 LoginUser객체 저장
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("디버그 : JwtVerifiyFilter 동작함");
        // 1. 서블릿 -> http서블릿
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        // 2. 요청 헤더에 jwt헤더 가져오기
        String prefixJwt = req.getHeader(JwtProvider.HEADER); //가져오기위해 dto를 활용한것
        // 3. 헤더 없으면 예외처리 400
        if(prefixJwt == null){
            error(resp,new Exception400("토큰이 전달되지 않았습니다."));
            return;
        }
        // 4. bearare만 제거한다.
        String jwt = prefixJwt.replace(JwtProvider.TOKEN_PREFIX,"");

        // 5. jwt를 전체적으로 디코딩한다 (base64만 디코딩)
        //      디코딩 한 값에서, id, role 가져오기
        try {
            DecodedJWT decodedJwt = JwtProvider.verify(jwt);
            int id = decodedJwt.getClaim("id").asInt();
            String role = decodedJwt.getClaim("role").asString();
            // 6. 세션 사용, 위에 id,role로 세션에 담을 객체 생성(login객체),세션에 담기
            HttpSession session = req.getSession();
            LoginUser loginUser = LoginUser.builder().id(id).role(role).build();
            session.setAttribute("loginUser", loginUser);
            // 7. 다음 처리할 필터가 있을수있기에 chain.doFilter()사용
            chain.doFilter(req, resp);
            // 8. 디코딩하는 과정까지는 적어도 try캐치로 처리해야되고, 토큰만료,시크릿키검증실패 예외처리
        }catch (SignatureVerificationException sve){
            error(resp,sve);
        }catch (TokenExpiredException tee){
            error(resp,tee);
        }
    }

    //error메서드 작성, IOException으로 던져라.
    public void error(HttpServletResponse resp,Exception e) throws IOException{
        // 1. 응답, 상태 401, 콘텐트타입 json 설정
        resp.setStatus(401);
        resp.setContentType("application/json; charset=utf-8");
        // 2. 응답 dto를 사용, 응답 메서드중 fail메서드를 사용해서 응답
        ResponseDto<?>responseDto = new ResponseDto<>().fail(HttpStatus.UNAUTHORIZED,"인증 안됨",e.getMessage());
        // 3. 오브젝트매핑으로 fail dto객체를 직렬화시켜서. getWrite().println()으로 출력스트림에 문자열 출력
        ObjectMapper om = new ObjectMapper();
        String responseBody = om.writeValueAsString(responseDto);
        resp.getWriter().println(responseBody);
    }
}
