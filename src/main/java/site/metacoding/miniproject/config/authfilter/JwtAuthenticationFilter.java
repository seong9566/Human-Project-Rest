package site.metacoding.miniproject.config.authfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.exception.ApiException;
import site.metacoding.miniproject.utill.JWTToken.CookieForToken;
import site.metacoding.miniproject.utill.JWTToken.TokenVerificationForCookie;

@Slf4j
public class JwtAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        TokenVerificationForCookie tokenVerification = new TokenVerificationForCookie();
        String token = CookieForToken.cookieToToken(req.getCookies());

        if (!req.getMethod().equals("POST")) {
            throw new ApiException("잘못된 접근입니다. POST 메서드로 접근해주세요");
        }

        if (tokenVerification.Verification(token)) {
            throw new ApiException("만료되지않은 토큰값이 존재합니다");
        }
        
        //이터레이터 값 확인
        // req.getAttributeNames().asIterator()
        // .forEachRemaining(attribute -> log.debug("디버그 : " + attribute + " : " +
        // req.getAttribute(attribute)));
        // req.getParameterMap().forEach((key, value) -> log.debug("디버그 : " +
        // req.getParameter(key) + " = " + value));

        chain.doFilter(req, resp);
    }

}
