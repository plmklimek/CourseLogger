package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.enums.Role;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllStudents() {
        return userRepository.findAll().stream().filter(user -> {
            return user.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals(Role.STUDENT.getName()));
        }).collect(Collectors.toList());
    }

    public List<User> getAllTeachers() {
        return userRepository.findAll().stream().filter(user -> {
            return user.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals(Role.TEACHER.getName()));
        }).collect(Collectors.toList());
    }

}
