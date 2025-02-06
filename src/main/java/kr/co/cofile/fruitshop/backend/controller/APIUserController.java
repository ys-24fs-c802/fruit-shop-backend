package kr.co.cofile.fruitshop.backend.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.cofile.fruitshop.backend.component.CustomUserDetails;
import kr.co.cofile.fruitshop.backend.component.JwtUtil;
import kr.co.cofile.fruitshop.backend.dto.UserDTO;
import kr.co.cofile.fruitshop.backend.service.APIUserService;
import kr.co.cofile.fruitshop.backend.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class APIUserController {
    @Value("${jwt.refresh-token-expiration-time}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;

    private final APIUserService apiUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {
        apiUserService.signup(userDTO);
        return ResponseEntity.ok("Signup successful");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> user, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.get("username"), user.get("password"))
            );

            String username = authentication.getName();
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String accessToken = jwtUtil.generateAccessToken(username, roles);
            String refreshToken = jwtUtil.generateRefreshToken(username);

            // HttpOnly 쿠키 설정 (Refresh Token)
            Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge((int) REFRESH_TOKEN_EXPIRATION_TIME / 1000);
            response.addCookie(refreshTokenCookie);

            return ResponseEntity.ok(Map.of("accessToken", accessToken));

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> tokens) {
        String refreshToken = tokens.get("refreshToken");

        if (!jwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid Refresh Token"));
        }

        String username = jwtUtil.extractUsername(refreshToken);
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String newAccessToken = jwtUtil.generateAccessToken(username, roles);
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }



    @GetMapping("/user/{username}")
    public ResponseEntity<String> user(@PathVariable String username,
                                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        // 인증된 사용자 확인
        String authenticatedUsername = userDetails.getUsername();

        // 요청한 username과 인증된 사용자가 일치하는지 확인
        if (!authenticatedUsername.equals(username)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("권한이 없습니다.");
        }

        return ResponseEntity.ok("사용자 페이지: " + username);
    }
}
