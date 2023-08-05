package wanted.security.domain;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wanted.community.user.domain.Role;
import wanted.community.user.domain.User;

import java.util.Collection;
import java.util.List;



public class CustomUserDetails implements UserDetails {
    private final String email;
    private final String password;
    private final Role role;

    @Builder
    public CustomUserDetails(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static UserDetails create(User user) {
        return CustomUserDetails.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
