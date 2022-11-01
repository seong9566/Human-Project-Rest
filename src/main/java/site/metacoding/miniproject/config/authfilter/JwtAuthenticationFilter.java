package site.metacoding.miniproject.config.authfilter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.config.handler.exception.ApiException;
import site.metacoding.miniproject.service.users.UsersService;
import site.metacoding.miniproject.web.dto.request.etc.LoginDto;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final UsersService usersService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        ObjectMapper om = new ObjectMapper();
        // req.getAttributeNames().asIterator()
        // .forEachRemaining(attribute -> log.debug("디버그 : " + attribute + " : " +
        // req.getAttribute(attribute)));

        req.getParameterMap().forEach((key, value) -> log.debug("디버그 : " + req.getParameter(key) + " = " + value));

        // LoginDto body = om.readValue(req.getInputStream(), LoginDto.class);

        if (!req.getMethod().equals("POST")) {
            throw new ApiException("잘못된 접근입니다. POST 메서드로 접근해주세요");
        }

        chain.doFilter(req, resp);
    }

}
