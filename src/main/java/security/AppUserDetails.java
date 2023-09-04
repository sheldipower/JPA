package security;

import dto.AppUserDto;
import org.hibernate.mapping.List;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class AppUserDetails implements UserDetails {

    private AppUserDto userDetails;

    public  void setUserDetails(AppUserDto userDetails) {
        this.userDetails = userDetails;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(userDetails)
                .map(AppUserDto::getRole)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(List::of)
                .orElse(Collections.emptyList());
    }
    @Override
    public String getPassword() {
        return Optional.ofNullable(userDetails)
                .map(AppUserDto::getPassword)
                .orElse(null);
    }
    @Override
    public String getUsername() {
        return Optional.ofNullable(userDetails)
                .map(AppUserDto::getLogin)
                .orElse(null);
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
