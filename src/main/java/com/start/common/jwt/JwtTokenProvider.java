package com.start.common.jwt;

//jwt 생성/파싱 담당
// 비밀 키, 만료 시간, 토큰 생성/파싱 메서드

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}") // 🔐 application.yml에서 비밀 키 로드
    private String secretKey;

    private SecretKey key;

    // ⏱️ Access Token: 1시간
    private final long accessTokenValidityInMs = 1000L * 60 * 60;

    // ⏱️ Refresh Token: 14일
    private final long refreshTokenValidityInMs = 1000L * 60 * 60 * 24 * 14;

    // ✅ WAS 시작 시, secretKey를 디코딩해서 SecretKey로 초기화
    @PostConstruct
    protected void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // ✅ Access & Refresh Token을 동시에 생성해서 반환
    public Map<String, String> createToken(String username, String role) {
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", generateAccessToken(username, role));
        tokens.put("refreshToken", generateRefreshToken(username));
        return tokens;
    }

    // ✅ Access Token 생성
    public String generateAccessToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessTokenValidityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Refresh Token 생성 (role은 넣지 않음)
    public String generateRefreshToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date expiry = new Date(now.getTime() + refreshTokenValidityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ 토큰에서 사용자 이름(주체) 추출
    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            exceptionHandler.handle(e);
            return null;
        }
    }

    // ✅ 토큰 유효성 검증 (서명, 만료 시간 등 확인)
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            exceptionHandler.handle(e);
            return false;
        }
    }

    // ✅ HTTP Request 헤더에서 Bearer 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // "Bearer " 제거
        }
        return null;
    }
}