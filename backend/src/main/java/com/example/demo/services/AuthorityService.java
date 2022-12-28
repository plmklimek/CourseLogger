package com.example.demo.services;

import com.example.demo.models.Authority;
import com.example.demo.models.User;
import com.example.demo.models.enums.Role;
import com.example.demo.repositories.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public Authority createAuthority(User user, Role role) {
        Authority authority = new Authority();
        authority.setAuthority(role.getName());
        authority.setUser(user);
        return authorityRepository.save(authority);
    }
}
