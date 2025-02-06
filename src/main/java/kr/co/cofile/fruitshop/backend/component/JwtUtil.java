package kr.co.cofile.fruitshop.backend.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import kr.co.cofile.fruitshop.backend.dto.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

//    @Value("${jwt.expiration-time}")// 보안성을 위해 환경변수로 관리 권장
//    private long EXPIRATION_TIME;    // 1일 (ms)

    @Value("${jwt.access-token-expiration-time}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;

    @Value("${jwt.refresh-token-expiration-time}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;

    private Claims getClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 토큰 생성
    public String generateToken(String username, List<String> roles, long expirationTime) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        String rolesStr = String.join(",", roles);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", rolesStr)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    // Access Token 생성
    public String generateAccessToken(String username, List<String> roles) {
        return generateToken(username, roles, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    // Refresh Token 생성 (role 없이)
    public String generateRefreshToken(String username) {
        return generateToken(username, Collections.emptyList(), REFRESH_TOKEN_EXPIRATION_TIME);
    }

    // JWT 토큰에서 사용자 정보 추출
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // JWT 토큰에서 권한 정보 추출
    public List<String> extractRoles(String token) {
        String roles =  getClaimsFromToken(token).get("roles", String.class);

        return Arrays.stream(roles.split(","))
                .map(UserRole::fromRoleName)
                .collect(Collectors.toList());
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        return !extractClaims(token).getExpiration().before(new Date());
    }

    // Claims 추출
    private Claims extractClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
