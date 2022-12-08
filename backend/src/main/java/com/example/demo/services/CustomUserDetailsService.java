package com.example.demo.services;

import com.example.demo.models.AppUserDetails;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private static final String USER_DOESNT_EXISTS = "User doesnt exists";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException(USER_DOESNT_EXISTS);
        }
        return new AppUserDetails(user);
    }
}