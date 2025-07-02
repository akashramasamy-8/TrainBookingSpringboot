package com.project.app.service;

import com.project.app.model.Users;
import com.project.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
    public class AuthService {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private UserRepository userRepository;
        public void register(Users user){
            user.setPassword(passwordEncoder.encode(user.getPassword()))    ;
            userRepository.save(user);
        }
}
