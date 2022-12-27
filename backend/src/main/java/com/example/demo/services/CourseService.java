package com.example.demo.services;

import com.example.demo.models.Course;
import com.example.demo.models.dtos.CourseDto;
import com.example.demo.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    private static final String COURSE_DOESNT_EXISTS = "COURSE DOESNT EXISTS";

    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream().map(CourseDto::new
        ).collect(Collectors.toList());
    }

    public CourseDto getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException((COURSE_DOESNT_EXISTS)));
        return new CourseDto(course).loadDetails(course.getStudents(),
                course.getTeacher());
    }
}
