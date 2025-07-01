package com.start.common.jwt;
//Bearer 인증 방식 - Access Token을 HTTP 요청의 Authorization(권한 부여) 헤더에 포함하여 전송한다.
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    private String grantType; //JWT에 대한 인증 타입
    private String accessToken;
    private String refreshToken;
}

