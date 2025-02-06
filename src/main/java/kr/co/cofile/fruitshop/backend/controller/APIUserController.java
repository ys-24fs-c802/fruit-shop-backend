package kr.co.cofile.fruitshop.backend.controller;

import kr.co.cofile.fruitshop.backend.component.CustomUserDetails;
import kr.co.cofile.fruitshop.backend.component.JwtUtil;
import kr.co.cofile.fruitshop.backend.dto.UserDTO;
import kr.co.cofile.fruitshop.backend.service.APIUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class APIUserController {
    private final APIUserService apiUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {
        apiUserService.signup(userDTO);
        return ResponseEntity.ok("Signup successful");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.get("username"), user.get("password"))
            );

            String username = authentication.getName();
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtUtil.generateToken(username, roles);
            return Map.of("token", token);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }
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
