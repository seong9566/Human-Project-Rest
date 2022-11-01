package site.metacoding.miniproject.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject.config.authfilter.JwtAuthenticationFilter;
import site.metacoding.miniproject.service.users.UsersService;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final UsersService usersService;

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterRegister() {
        FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<>(
                new JwtAuthenticationFilter(usersService));
        bean.addUrlPatterns("/login");
        bean.setOrder(1);
        return bean;
    }
}
