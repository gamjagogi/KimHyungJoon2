package shop.mtcoding.kimhyungjoon2.core.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import shop.mtcoding.kimhyungjoon2.model.user.User;

import java.util.Date;

public class JwtProvider {

    private static final String SUBJECT = "jwtstudy";
    private static final int EXP = 1000 * 60 * 60;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER = "Authorization";
    private static final String SECRET = "감자고기";

    public static String create(User user){
        String jwt = JWT.create()
                .withSubject(SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXP)) //현재시간+ 만료시간
                .withClaim("id",user.getId())
                .withClaim("role",user.getRole())
                .sign(Algorithm.HMAC512(SECRET));
        System.out.println("디버그 : 토큰 생성됨");
        return TOKEN_PREFIX + jwt;
    }

    public static DecodedJWT verify(String jwt) throws SignatureVerificationException, TokenExpiredException {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET))
                .build().verify(jwt); //토큰은 단방향이다. 디코딩이 불가. 시크릿키 확인후 전체적으로 동일성만 체크
        System.out.println("디버그 : 토큰 검증됨");
        return decodedJWT;
    }
}
