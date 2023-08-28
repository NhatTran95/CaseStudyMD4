package com.cg.model;

import com.cg.model.enums.EUserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String phone;
    private final String username;
    private final String password;

    private final Collection<? extends GrantedAuthority> roles;

    private final String role;

    public UserPrinciple(Long id, String phone, String username, String password, Collection<? extends GrantedAuthority> roles, EUserRole role) {
        this.id = id;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.role = role.getValue();
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().getCode());
        authorities.add(authority);

        return new UserPrinciple(
                user.getId(),
                user.getPhone(),
                user.getUsername(),
                user.getPassword(),
                authorities,
                user.getUserRole().getName()
        );
    }

    public Long getId() {
        return id;
    }


    public String getPhone() {
        return phone;
    }

    public String getFullName() {
        return username;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getRole() {
        return role;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
