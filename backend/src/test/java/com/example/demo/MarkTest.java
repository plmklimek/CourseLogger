package com.example.demo;

import com.example.demo.models.dtos.MarkCreate;
import com.example.demo.repositories.MarkRepository;
import com.example.demo.services.CourseService;
import com.example.demo.services.MarksService;
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
public class MarkTest {
    @InjectMocks
    private MarksService marksService;

    @Mock
    private CourseService courseService;

    @Mock
    private UserService userService;

    @Mock
    private MarkRepository markRepository;

    @Test
    void createMark() {
        MarkCreate createMark = new MarkCreate(2.0, 1L, 2L, 1L);
        doReturn(Modals.getTeacher()).when(userService).getTeacherByIdUser(any());
        doReturn(Modals.getStudent()).when(userService).getStudentByIdUser(any());
        doReturn(Modals.getCourse()).when(courseService).getByIdCourse(any());
        assertDoesNotThrow(() -> {
            marksService.createMark(createMark);
        });
    }
}
