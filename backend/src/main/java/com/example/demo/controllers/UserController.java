package com.example.demo.controllers;

import com.example.demo.models.ErrorModal;
import com.example.demo.services.CustomUserDetailsService;
import com.example.demo.services.UserService;
import com.example.demo.utils.LoggedUser;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {
    private final CustomUserDetailsService customUserDetailsService;

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<?> loginIn() {
        try {
            String loggedUser = LoggedUser.getLoggedUser();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(customUserDetailsService.loadUserByUsername(loggedUser));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ErrorModal(exception.getMessage()));
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.getAllStudents());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ErrorModal(exception.getMessage()));
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.getStudentById(id));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ErrorModal(exception.getMessage()));
        }
    }

    @GetMapping("/teachers")
    public ResponseEntity<?> getTeachers() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.getAllTeachers());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ErrorModal(exception.getMessage()));
        }
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userService.getTeacherById(id));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ErrorModal(exception.getMessage()));
        }
    }

}
