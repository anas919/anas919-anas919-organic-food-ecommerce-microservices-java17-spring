package com.micro.gateway.service;


import com.micro.gateway.dto.LoginUserRequest;
import com.micro.gateway.dto.RegisterUserRequest;
import com.micro.gateway.model.Role;
import com.micro.gateway.model.User;
import com.micro.gateway.repository.RoleRepository;
import com.micro.gateway.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserRequest input) {
        Optional<Role> optionalRole = roleRepository.findById(input.getRole_id());
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            User user = new User();
            user.setName(input.getName());
            user.setEmail(input.getEmail());
            user.setPassword(passwordEncoder.encode(input.getPassword()));
            user.setRole(role);
            return userRepository.save(user);
        }else{
            throw new IllegalArgumentException("Role not found with ID: " + input.getRole_id());
        }
    }

    public User authenticate(LoginUserRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}