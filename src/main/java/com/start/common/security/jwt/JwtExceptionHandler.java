package com.start.common.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtExceptionHandler {

    // 예외 핸들링: 로그 수준은 WARN으로 통일
    public void handle(Exception e) {
        if (e instanceof SecurityException || e instanceof MalformedJwtException) {
            log.warn("❌ 잘못된 JWT 서명 또는 구조: {}", e.getMessage());
        } else if (e instanceof ExpiredJwtException) {
            log.warn("⏰ 만료된 JWT 토큰: {}", e.getMessage());
        } else if (e instanceof UnsupportedJwtException) {
            log.warn("🚫 지원하지 않는 JWT 토큰: {}", e.getMessage());
        } else if (e instanceof IllegalArgumentException) {
            log.warn("⚠️ JWT 클레임이 비어 있음: {}", e.getMessage());
        } else {
            log.warn("❗ 알 수 없는 JWT 오류", e);
        }
    }
}