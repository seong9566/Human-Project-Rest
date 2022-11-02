package site.metacoding.miniproject.utill;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.config.handler.exception.ApiException;
import site.metacoding.miniproject.dto.user.UserRespDto.SignCompanyDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignPersonalDto;
import site.metacoding.miniproject.dto.user.UserRespDto.SignedDto;

@Slf4j
public class JWTToken {

    public static class CreateJWTToken {

        static Date expire = new Date(System.currentTimeMillis() + (1000 * 60 * 60));

        public static String createToken(SignedDto<?> signedDto) {

            HashMap<String, Object> map = new HashMap<>();
            map.put("usersId", signedDto.getUsersId());
            map.put("LoginId", signedDto.getLoginId());

            // casting exception 발생
            try {
                SignCompanyDto signCompanyDto = (SignCompanyDto) signedDto.getUserInfo();
                map.put("companyId", signCompanyDto.getCompanyId());

            } catch (Exception e) {
                SignPersonalDto signPersonalDto = (SignPersonalDto) signedDto.getUserInfo();
                map.put("personalId", signPersonalDto.getPersonalId());
            }

            String jwtToken = JWT.create()
                    .withSubject("userinfo")
                    .withExpiresAt(expire)
                    .withClaim("sigendDto", map)
                    .sign(Algorithm.HMAC512(SecretKey.SECRETKEY.key()));

            return jwtToken;
        }
    }

    public static class CookieForToken {

        public static Cookie setCookie(String token) {
            Cookie cookie = new Cookie("Authorization", token); // Cookie에 Bearer 추가하면 안됨 - 최대 공간 초과....

            return cookie;
        }

        public static String cookieToToken(Cookie[] cookies) {
            String token = null;

            if (cookies == null) {
                return null;
            }

            // 쿠키내의 토큰 찾기
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Authorization"))
                    token = cookie.getValue();
            }
            return token;
        }

    }

    public static class TokenVerification {

        public Boolean Verification(String token) {

            if (token == null) {
                return false;
            }

            // 토큰 검증 - 검증전 공백제거
            token = token.replace("Authorization=", "");
            token = token.trim();
            try {

                log.debug("디버그 : 토큰확인 - " + token);

                Date now = new Date(System.currentTimeMillis());

                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SecretKey.SECRETKEY.key())).build().verify(token);

                // log.debug("디버그 : 만료시간 - " + decodedJWT.getExpiresAt().toString());
                // log.debug("디버그 : 현재시간 - " + now);
                // 입력받은 토큰값이 현재시간을 넘지 않았을 경우 true를 반환 - 만료된 토큰이 아닌지 판별

                if (decodedJWT.getExpiresAt() != null && decodedJWT.getExpiresAt().after(now)) {
                    return true;
                }

            } catch (Exception e) {
                throw new ApiException("잘못된 토큰이 입력되었습니다.");
            }

            return false;

        }
    }

    public static class TokenToSinedDto {
        Integer usersId = null;
        String LoginId = null;
        SignPersonalDto signPersonalDto = new SignPersonalDto();
        SignCompanyDto signCompanyDto = new SignCompanyDto();

        public SignedDto<?> tokenSignedDto(Map<String, Object> getSigned) {
            for (String key : getSigned.keySet()) {

                if (key.equals("usersId")) {

                    usersId = ((Integer) getSigned.get(key));

                } else if (key.equals("loginId")) {

                    LoginId = (getSigned.get(key).toString());

                } else if (key.equals("personalId")) {

                    signPersonalDto.setPersonalId((Integer) getSigned.get(key));

                } else if (key.equals("companyId")) {

                    signCompanyDto.setCompanyId((Integer) getSigned.get(key));

                }
            }

            if (signPersonalDto.getPersonalId() != null) {
                return new SignedDto<>(usersId, LoginId, signPersonalDto);
            } else {
                return new SignedDto<>(usersId, LoginId, signCompanyDto);
            }
        }

    }

}
