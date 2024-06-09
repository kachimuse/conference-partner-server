package cn.edu.ecnu.conferencepartner.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * JWT 令牌工具类
 * @author 龚奕玮
 * @since 2024-06-09
 */
@RequiredArgsConstructor
public class JwtUtil {

    /**
     * 生成 JWT 令牌
     */
    public static String createJWT(String secretString, Long timeToLive, Map<String, Object> claims) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
        Date issueTime = new Date();
        Date expireTime = new Date(System.currentTimeMillis() + timeToLive * 1000);

        return Jwts.builder()
                .issuedAt(issueTime)
                .notBefore(issueTime)
                .expiration(expireTime)
                .claims(claims)
                .signWith(key)
                .compact();
    }

    /**
     * 解析 JWT 令牌
     */
    public static Claims parseJWT(String secretString, String token) throws JwtException {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
