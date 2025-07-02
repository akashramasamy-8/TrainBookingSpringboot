package com.project.app.controller;

import com.project.app.jwt.JwtUtil;
import com.project.app.model.Users;
import com.project.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;



    @PostMapping("/register")
    public ResponseEntity<String> adduser(@RequestBody Users user){
        authService.register(user);
        return ResponseEntity.ok("User added successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Users user){
        System.out.println("LOGIN TRY: " + user.getUsername() + " / " + user.getPassword());

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            System.out.println("AUTH SUCCESS: " + auth.getPrincipal());

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails,user.getId());
            return ResponseEntity.ok(Map.of("token", token));
        } catch(Exception e){
            System.out.println("AUTH FAILED: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","username or password incorrect"));
        }
    }

}
