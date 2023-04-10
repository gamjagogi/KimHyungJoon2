package shop.mtcoding.kimhyungjoon2.model.log.login;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "login_log_tb")
public class LoginLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//로그인 칼럼에서 identity id, auto_increament
    private Long userId;
    private String userAgent;
    private String clientIP;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    public LoginLog(Long id, Long userId, String userAgent, String clientIP, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.userAgent = userAgent;
        this.clientIP = clientIP;
        this.createdAt = createdAt;
    }
}
