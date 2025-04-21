package com.uade.tpo.E_Commerce.controllers.config;
import com.uade.tpo.E_Commerce.entity.Basic_User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;


public class UserPrincipal implements UserDetails {

    private final Basic_User user;

    public UserPrincipal(Basic_User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> user.getUser_roles().getRoleName()); // Asegurate que getRole() devuelva "ADMIN", "USER", etc
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getMail();
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

    public String getRole() {
        return user.getUser_roles().getRoleName(); // esto lo vas a usar en JwtService
    }

    public Basic_User getUser() {
        return user;
    }
}
