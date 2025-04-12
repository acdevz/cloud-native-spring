package org.tacos.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tacos.models.User;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullName;
    private String zip;
    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.of(username, passwordEncoder.encode(password), fullName, zip, phoneNumber);
    }
}
