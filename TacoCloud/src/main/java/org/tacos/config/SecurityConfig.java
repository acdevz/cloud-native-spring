package org.tacos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.tacos.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@EnableGlobalAuthentication // This annotation is not necessary in Spring Boot 2.0 and later
public class SecurityConfig{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @Qualifier("userDetailsServiceWithInMemory")
//    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        List<UserDetails> usersList = new ArrayList<>();
//        usersList.add(new User( "buzz", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        usersList.add(new User( "woody", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        return new InMemoryUserDetailsManager(usersList);
//    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            org.tacos.models.User user = userRepository.findByUsername(username);
            if(user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.GET, "/api/ingredients").hasAuthority("SCOPE_viewIngredients")
                                .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                                .requestMatchers(HttpMethod.DELETE, "/api/ingredients/{id}").hasAuthority("SCOPE_deleteIngredients")
                )
                .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf ->
                        csrf.ignoringRequestMatchers("/h2-console/**", "/api/**", "/actuator/**")
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/actuator/**").hasRole("ADMIN")
                                .requestMatchers("/design", "/orders", "/orders/**").hasRole("USER")
                                .requestMatchers("/", "/**").permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/design", true)
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
