package com.example.demo.controllers;

import com.example.demo.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/test")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(courseService.getAll());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
