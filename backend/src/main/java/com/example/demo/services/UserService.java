package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.dtos.users.UserDto;
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

    private static final String USER_DOESNT_EXISTS = "USER DOESNT EXISTS";

    public List<UserDto> getAllStudents() {
        return userRepository.findAll().stream().filter(user -> {
            return user.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals(Role.STUDENT.getName()));
        }).map(UserDto::new).collect(Collectors.toList());
    }

    public List<UserDto> getAllTeachers() {
        return userRepository.findAll().stream().filter(user -> {
            return user.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals(Role.TEACHER.getName()));
        }).map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto getTeacherById(Long id) {
        User user =
                userRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException((USER_DOESNT_EXISTS)));
        if (user.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(Role.TEACHER.getName()))) {
            return new UserDto(user);
        } else {
            throw new IllegalArgumentException(USER_DOESNT_EXISTS);
        }
    }

    public UserDto getStudentById(Long id) {
        User user =
                userRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException((USER_DOESNT_EXISTS)));
        if (user.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(Role.STUDENT.getName()))) {
            return new UserDto(user);
        } else {
            throw new IllegalArgumentException(USER_DOESNT_EXISTS);
        }
    }
}
