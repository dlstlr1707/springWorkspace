package com.example.cookieprc.config;

import com.example.cookieprc.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<LoggingFilter> filterRegistrationBean() {
        FilterRegistrationBean<LoggingFilter> filterRegistration = new FilterRegistrationBean<>();

        // 패턴 지정하는 여러가지 방식 중에 하나임 다른 방식도 있음
        filterRegistration.setFilter(new LoggingFilter());
        filterRegistration.addUrlPatterns("/main/*");
        filterRegistration.setOrder(1);

        return filterRegistration;
    }
}
