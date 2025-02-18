package kr.co.cofile.fruitshop.backend.component;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private final Integer userId;
    private final String username;
    private final String password;
    private final Boolean enabled;
    private final List<GrantedAuthority> authorities;

    // 어드민 인증용 생성자
    public CustomUserDetails(Integer userId,
                             String username,
                             String password,
                             Boolean enabled,
                             List<GrantedAuthority> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = roles;
    }

    // JWT 인증용 생성자
    public CustomUserDetails(String username, List<String> roles) {
        this.userId = null;
        this.username = username;
        this.password = null;
        this.enabled = true;
        this.authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
