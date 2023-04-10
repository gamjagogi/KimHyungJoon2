package shop.mtcoding.kimhyungjoon2.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import shop.mtcoding.kimhyungjoon2.dto.ResponseDto;

@Getter
public class Exception400 extends RuntimeException {
    public Exception400(String message){
        super(message);
    }

    public ResponseDto<?> body(){
        ResponseDto<String>responseDto = new ResponseDto<>();
        responseDto.fail(HttpStatus.BAD_REQUEST,"badReaquest",getMessage());
        return responseDto;
    }

    public HttpStatus status(){
        return HttpStatus.BAD_REQUEST;
    }
}
