package com.example.demo.services;

import com.example.demo.models.StudentCourse;
import com.example.demo.repositories.StudentCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentCourseService {
    private final StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> getAll() {
        return studentCourseRepository.findAll();
    }
}
