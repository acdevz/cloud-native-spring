package org.auth.authorizationserver.config;

import org.auth.authorizationserver.models.User;
import org.auth.authorizationserver.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectConfig {
    @Bean
    public ApplicationRunner dataLoader(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){
        return args -> {
            userRepository.save(User.of("chef_acdevs", passwordEncoder.encode("4444"), "ROLE_ADMIN"));
        };
    }
}
