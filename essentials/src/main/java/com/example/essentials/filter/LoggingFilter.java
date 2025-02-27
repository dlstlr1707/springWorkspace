package com.example.essentials.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 (필요시)
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        // ** 필수로 작성해야 함 (실질적인 로직이 들어감)

        // URI 포맷팅 없이 바로 사용하기 위해 형변환 진행
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // 요청정보 로깅
        System.out.println("Request URI : " + request.getRequestURI());
        System.out.println("Request Method : " + request.getMethod());

        // 필터 체인 : 계속해서 다음 필터 또는 서블릿으로 전달
        filterChain.doFilter(servletRequest, servletResponse);
        
        // 응답 상태 코드 로깅
        System.out.println("Response Status : " + response.getStatus());
        
    }

    @Override
    public void destroy() {
        // 필터 종료 시 처리 (필요시)
    }
}
