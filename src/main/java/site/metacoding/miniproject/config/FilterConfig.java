package site.metacoding.miniproject.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import site.metacoding.miniproject.config.authfilter.JwtAuthenticationFilter;
import site.metacoding.miniproject.config.authfilter.JwtAuthorizationFilter;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilterRegister() {
        FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<>(
                new JwtAuthenticationFilter());
        bean.addUrlPatterns("/login");
        bean.setOrder(2);
        return bean;
    }
    
    @Bean
    public FilterRegistrationBean<JwtAuthorizationFilter> jwtAuthorizationFilterRegister() {
        FilterRegistrationBean<JwtAuthorizationFilter> bean = new FilterRegistrationBean<>(
                new JwtAuthorizationFilter());
        bean.addUrlPatterns("/s/*");
        bean.setOrder(1);
        return bean;
    }
}
