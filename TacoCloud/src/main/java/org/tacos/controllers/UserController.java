package org.tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.tacos.dto.RegistrationForm;
import org.tacos.repositories.UserRepository;

@Slf4j
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String processRegistration(RegistrationForm form, Model model) {
        if (userRepository.findByUsername(form.getUsername()) != null) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        userRepository.save(form.toUser(passwordEncoder));
        log.info("User registered: {}", form);
        return "redirect:/login";
    }
}
