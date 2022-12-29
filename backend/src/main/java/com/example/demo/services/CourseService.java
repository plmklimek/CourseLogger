package com.example.demo.services;

import com.example.demo.models.Course;
import com.example.demo.models.User;
import com.example.demo.models.dtos.CourseDto;
import com.example.demo.models.dtos.CreateCourse;
import com.example.demo.models.enums.Role;
import com.example.demo.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    private final UserService userService;

    private static final String COURSE_DOESNT_EXISTS = "COURSE DOESNT EXISTS";

    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream().map(CourseDto::new
        ).collect(Collectors.toList());
    }

    public Course getByIdCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException((COURSE_DOESNT_EXISTS)));
    }

    public CourseDto getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException((COURSE_DOESNT_EXISTS)));
        return new CourseDto(course).loadDetails(course.getStudents(),
                course.getTeacher());
    }

    public Course createCourse(CreateCourse course) {
        User user = userService.getTeacherByIdUser(course.getTeacherId());
        Course courseToCreate = new Course();
        courseToCreate.setName(course.getName());
        courseToCreate.setTeacher(user);
        return courseRepository.save(courseToCreate);
    }

    public List<CourseDto> getUserCourses(String name) {
        User user = userService.getUserByEmail(name);
        if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(
                Role.TEACHER.getName()))) {
            return user.getConductedCourses().stream().map(CourseDto::new).collect(
                    Collectors.toList());
        } else if (user.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(
                        Role.STUDENT.getName()))) {
            return user.getCourses().stream()
                    .map(userCourse -> new CourseDto(userCourse.getCourse())).collect(
                            Collectors.toList());
        }
        return new ArrayList<CourseDto>();
    }
}
