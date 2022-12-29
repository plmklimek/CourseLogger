package com.example.demo;

import com.example.demo.models.dtos.CreateCourse;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.services.CourseService;
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
public class CourseTest {
    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserService userService;

    @Test
    void createCourse() {
        CreateCourse createCourse = new CreateCourse(1L, "Test", 2L, null);
        doReturn(Modals.getTeacher()).when(userService).getTeacherByIdUser(any());
        assertDoesNotThrow(() -> {
            courseService.createCourse(createCourse);
        });
    }
}
