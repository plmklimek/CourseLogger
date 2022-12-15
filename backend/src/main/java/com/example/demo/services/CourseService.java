package com.example.demo.services;

import com.example.demo.models.Course;
import com.example.demo.models.StudentCourse;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.StudentCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAll(){
        return courseRepository.findAll();
    }
}
