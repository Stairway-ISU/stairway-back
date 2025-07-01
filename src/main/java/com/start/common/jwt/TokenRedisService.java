package com.start.common.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenRedisService {

    private final StringRedisTemplate redisTemplate;

    private static final String PREFIX = "RT:";

    // ✅ 저장
    public void saveRefreshToken(String username, String refreshToken, long validityMs) {
        redisTemplate.opsForValue().set(
                PREFIX + username,
                refreshToken,
                Duration.ofMillis(validityMs)
        );
    }

    // ✅ 조회
    public String getRefreshToken(String username) {
        return redisTemplate.opsForValue().get(PREFIX + username);
    }

    // ✅ 삭제
    public void deleteRefreshToken(String username) {
        redisTemplate.delete(PREFIX + username);
    }
}