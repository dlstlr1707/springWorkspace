package com.example.basicboardv2.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    // yml에 있는 데이터 접근 하기 위한 config ( @Value 말고 다른 방식 )
    private String issuer;
    private String secretKey;
}
