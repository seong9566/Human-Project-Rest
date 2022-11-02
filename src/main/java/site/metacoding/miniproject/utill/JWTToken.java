package site.metacoding.miniproject.utill;

import java.util.Date;

import javax.servlet.http.Cookie;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.config.handler.exception.ApiException;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;

@Slf4j
public class JWTToken {

    public static class CreateJWTToken {

        static Date expire = new Date(System.currentTimeMillis() + (1000 * 60 * 60));

        public static String createToken(SignedDto<?> signedDto) {
            String jwtToken = JWT.create()
                    .withSubject("userinfo")
                    .withExpiresAt(expire)
                    .withClaim("userId", signedDto.getUsersId())
                    .withClaim("loginId", signedDto.getLoginId())
                    .sign(Algorithm.HMAC512(SecretKey.SECRETKEY.key()));
            return jwtToken;
        }

        public static Cookie setCookie(String token) {
            Cookie cookie = new Cookie("Authorization", token); // Cookie에 Bearer 추가하면 안됨 - 최대 공간 초과....

            return cookie;
        }
        
        public static class TokenVerificationForCookie {

            public Boolean Verification(Cookie[] cookies) {
                try {

                    String token = null;

                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("Authorization"))
                            token = cookie.getValue();
                    }

                    // 토큰 검증 - 검증전 공백제거
                    token = token.replace("Authorization=", "");
                    token = token.trim();

                    log.debug("디버그 : 토큰확인 - " + token);

                    Date now = new Date(System.currentTimeMillis());

                    DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SecretKey.SECRETKEY.key())).build().verify(token);
                    
                    log.debug("디버그 : 만료시간 - " + decodedJWT.getExpiresAt().toString());
                    log.debug("디버그 : 현재시간 - " + now);
                    //입력받은 토큰값이 현재시간을 넘지 않았을 경우 true를 반환 - 만료된 토큰이 아닌지 판별
                    if (decodedJWT.getExpiresAt() != null && decodedJWT.getExpiresAt().after(now)) {
                        return true;
                    }
                    
                } catch (Exception e) {
                    throw new ApiException("잘못된 토큰이 입력되었습니다.");
                }

                return false;
                
            }
        }

    }
    
}
