package com.example.demo.controllers;

import com.example.demo.models.ErrorModal;
import com.example.demo.models.dtos.CreateCourse;
import com.example.demo.services.StudentCourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/studentsCourses")
public class StudentCourseController {
    private final StudentCourseService studentCourseService;

    @GetMapping("/test")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(studentCourseService.getAll());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(new ErrorModal(exception.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudentCourse(@RequestBody CreateCourse course) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(studentCourseService.createStudentCourse(course));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(new ErrorModal(exception.getMessage()));
        }
    }
}
