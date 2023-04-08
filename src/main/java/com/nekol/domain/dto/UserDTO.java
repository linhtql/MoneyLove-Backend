package com.nekol.domain.dto;

import com.nekol.domain.entity.User;
import com.nekol.payload.request.RegisterRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Data
@ToString
public class UserDTO implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private boolean isLoggedIn;

    public UserDTO () {

    }

    public UserDTO (RegisterRequest request) {
        username = request.getUsername();
        password = request.getPassword();
        isLoggedIn = false;

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(() -> "read");
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
