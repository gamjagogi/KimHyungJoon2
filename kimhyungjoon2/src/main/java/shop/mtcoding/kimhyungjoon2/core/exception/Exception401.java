package shop.mtcoding.kimhyungjoon2.core.exception;

import shop.mtcoding.kimhyungjoon2.dto.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;

//인증안됨
@Getter
public class Exception401 extends RuntimeException {
    public Exception401(String message){
        super(message);
    }

    //반환은 ResponseDto객체로 반환하는대, 담는 데이터의 타입은 아무거나 상관없다.
    //responsDto 인스턴스 내에 담는건 <String>으로 담겠다는거다.
    //상태코드 메서드를 담으면, 상태코드가 반환되서 String이다.
    public ResponseDto<?> body(){
        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.fail(HttpStatus.UNAUTHORIZED,"unAuthorized",getMessage());
        return responseDto;
    }

    public HttpStatus status(){
        return HttpStatus.UNAUTHORIZED;
    }
}
