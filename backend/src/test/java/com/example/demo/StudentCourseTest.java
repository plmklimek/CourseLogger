package com.example.demo;

import com.example.demo.models.dtos.CreateCourse;
import com.example.demo.repositories.StudentCourseRepository;
import com.example.demo.services.CourseService;
import com.example.demo.services.StudentCourseService;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class StudentCourseTest {
    @InjectMocks
    private StudentCourseService studentCourseService;

    @Mock
    private StudentCourseRepository studentCourseRepository;
    @Mock
    private CourseService courseService;

    @Mock
    private UserService userService;

    @Test
    void createStudentCourse() {
        CreateCourse course = new CreateCourse(1L, "KURS", Modals.getTeacher().getId(),
                null);
        doReturn(Modals.getStudent()).when(userService).getStudentByIdUser(any());
        doReturn(Modals.getCourse()).when(courseService).getByIdCourse(any());
        assertDoesNotThrow(() -> {
            studentCourseService.createStudentCourse(course);
        });
    }
}
