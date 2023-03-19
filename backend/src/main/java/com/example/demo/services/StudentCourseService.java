package com.example.demo.services;

import com.example.demo.models.Course;
import com.example.demo.models.StudentCourse;
import com.example.demo.models.User;
import com.example.demo.models.dtos.CreateCourse;
import com.example.demo.repositories.StudentCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentCourseService {
    private final StudentCourseRepository studentCourseRepository;

    private final CourseService courseService;

    private final UserService userService;

    public List<StudentCourse> getAll() {
        return studentCourseRepository.findAll();
    }

    public StudentCourse createStudentCourse(CreateCourse courseDto) {
        User student = userService.getStudentByIdUser(courseDto.getStudentId());
        Course course = courseService.getByIdCourse(courseDto.getId());
        if(!student.getCourses().contains(course)){
            return studentCourseRepository.save(new StudentCourse(student, course));
        }
        else{
            throw new IllegalArgumentException("Użytkownik jest przypisany już do tego przedmiotu");
        }
    }
}
