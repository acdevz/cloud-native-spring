package org.tacos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "`user`")
public class User implements UserDetails {
    @Serial
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String fullName;
    private String zip;
    private String phoneNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private List<String> roles = List.of("ROLE_USER");

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
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

    @Transient
    public static User of(String username, String password, String fullName, String zip, String phoneNumber) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setZip(zip);
        user.setPhoneNumber(phoneNumber);
        return user;
    }

    @Transient
    public static User of(String username, String password, String fullName, String zip, String phoneNumber, List<String> roles) {
        User user = of(username, password, fullName, zip, phoneNumber);
        user.setRoles(roles);
        return user;
    }
}
