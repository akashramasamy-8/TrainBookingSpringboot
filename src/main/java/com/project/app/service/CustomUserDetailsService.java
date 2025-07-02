    package com.project.app.service;

    import com.project.app.model.Users;
    import com.project.app.repository.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Component;
    import org.springframework.stereotype.Service;

    import java.util.Collections;
    import java.util.Optional;

    @Service
    public class CustomUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<Users> user=userRepository.findByusername(username);
            if(user.isEmpty()){
                throw new UsernameNotFoundException("User not found");
            }
            Users u=user.get();
            return new org.springframework.security.core.userdetails.User(u.getUsername(),u.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_"+u.getRole())));

        }
    }
