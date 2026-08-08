package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.JwtResponse;
import com.cfs.BookMyShow.dto.LoginRequest;
import com.cfs.BookMyShow.dto.SignupRequest;
import com.cfs.BookMyShow.entity.User;
import com.cfs.BookMyShow.repository.UserRepository;
import com.cfs.BookMyShow.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        String jwt = jwtUtils.generateJwtToken(userDetails);

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: User not found!"));

        return ResponseEntity.ok(new JwtResponse(jwt, user.getId(), user.getEmail(), user.getRole()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        // Create new user's account
        User user = User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .role(signUpRequest.getRole() != null ? signUpRequest.getRole() : "ROLE_USER")
                .build();

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }
}