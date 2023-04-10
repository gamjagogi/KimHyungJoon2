package shop.mtcoding.kimhyungjoon2.core.session;

import lombok.Builder;
import lombok.Getter;
//board에 들어오는 유저마다 먼저 session키가 있는지 부터본다.

@Getter
public class LoginUser {
    private Integer id;
    private String role;

    @Builder
    public LoginUser(Integer id, String role) {
        this.id = id;
        this.role = role;
    }
}
