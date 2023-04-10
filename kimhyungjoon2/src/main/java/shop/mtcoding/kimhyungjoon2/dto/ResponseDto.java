package shop.mtcoding.kimhyungjoon2.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDto<T> {

    private Integer status; // 상태코드
    private String msg; // 에러시에 의미있음 ex) badRequest
    private T data; // 에러시에 구체적인 에러 내용 ex) username이 입력되지 않았습니다.

    public ResponseDto(){
        this.status = HttpStatus.OK.value();
        this.msg = "성공";
        this.data = null;
    }

    public ResponseDto<?> data(T data){
        this.data = data;
        return this;
    }

    public ResponseDto<?> fail(HttpStatus httpStatus,String msg, T data){
        this.status = httpStatus.value();
        this.msg = msg; //에러제목
        this.data = data; //에러내용
        return this;
    }

}
