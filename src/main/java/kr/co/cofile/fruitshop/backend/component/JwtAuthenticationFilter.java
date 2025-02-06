package kr.co.cofile.fruitshop.backend.component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 요청 헤더에서 Authorization 값 가져오기
        final String authHeader = request.getHeader("Authorization");

        // Authorization 헤더가 존재하고 "Bearer "로 시작하는지 확인
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // "Bearer " 이후의 JWT 토큰 추출
            String jwtToken = authHeader.substring(7);

//            if (!jwtUtil.validateToken(jwtToken)) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Token expired or invalid");
//                return;  // 필터 체인 중단
//            }

            // JWT에서 사용자명(username)과 역할(roles) 정보 추출
            String username = jwtUtil.extractUsername(jwtToken);
            List<String> roles = jwtUtil.extractRoles(jwtToken);

            // 사용자명과 역할이 존재하면 인증 객체 생성
            if (username != null && roles != null) {
                // DB 조회 없이 JWT 정보로 UserDetails 객체 생성
                UserDetails userDetails = new CustomUserDetails(username, roles);

                // JWT가 유효한 경우, SecurityContextHolder에 인증 정보 저장
                if (jwtUtil.validateToken(jwtToken)) {
                    // roles(List<String>) -> SimpleGrantedAuthority 리스트로 변환
                    List<SimpleGrantedAuthority> authorities = roles.stream()
                            .map(role -> new SimpleGrantedAuthority(role)) // "USER" -> new SimpleGrantedAuthority("USER")
                            .collect(Collectors.toList());

                    // UsernamePasswordAuthenticationToken 생성 (인증된 사용자 객체)
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                    // SecurityContext에 인증 객체 등록
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        // 다음 필터로 요청 전달
        chain.doFilter(request, response);
    }

}
