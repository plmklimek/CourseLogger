package com.example.demo.controllers;

import com.example.demo.models.ErrorModal;
import com.example.demo.models.dtos.CreateCourse;
import com.example.demo.models.enums.Role;
import com.example.demo.services.CourseService;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/courses")
public class CourseController {

    private final UserService userService;

    private final CourseService courseService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(courseService.getAll());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(new ErrorModal(exception.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(courseService.getById(id));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(new ErrorModal(exception.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourse course,
                                          Principal principal) {
        try {
            if (userService.hasPermission(principal.getName(), Role.ADMIN))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(courseService.createCourse(course));
            return null;
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(new ErrorModal(exception.getMessage()));
        }
    }

    @GetMapping("/my")
    public ResponseEntity<?> getCourse(Principal principal) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(courseService.getUserCourses(principal.getName()));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(new ErrorModal(exception.getMessage()));
        }
    }
}
