package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.StudentCourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/studentsCourses")
public class StudentCourseController {
    private final StudentCourseService studentCourseService;

    @GetMapping("/test")
    public ResponseEntity getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentCourseService.getAll());
        }
        catch(IllegalArgumentException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
