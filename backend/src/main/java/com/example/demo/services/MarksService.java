package com.example.demo.services;

import com.example.demo.models.Course;
import com.example.demo.models.Mark;
import com.example.demo.models.User;
import com.example.demo.models.dtos.CourseDto;
import com.example.demo.models.dtos.MarkCreate;
import com.example.demo.models.dtos.MarkDto;
import com.example.demo.models.dtos.users.UserDto;
import com.example.demo.models.enums.Role;
import com.example.demo.repositories.MarkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MarksService {
    private final MarkRepository markRepository;
    private final CourseService courseService;

    private final UserService userService;

    public Map<CourseDto, List<MarkDto>> findByUserId(Long id) {
        return markRepository.findByUserId(id).stream().map(MarkDto::new)
                .collect(Collectors.groupingBy(MarkDto::getCourse));
    }

    public Map<UserDto, List<MarkDto>> findByCourseId(Long id) {
        return markRepository.findByCourseId(id).stream().map(MarkDto::new)
                .collect(Collectors.groupingBy(markDto -> markDto.getStudent()));
    }

    public Mark createMark(MarkCreate mark) {
        User student = userService.getStudentByIdUser(mark.getStudentId());
        User teacher = userService.getTeacherByIdUser(mark.getTeacherId());
        Course course = courseService.getByIdCourse(mark.getCourseId());
        Mark markToSave = new Mark();
        markToSave.setMark(mark.getMark());
        markToSave.setCourse(course);
        markToSave.setTeacher(teacher);
        markToSave.setStudent(student);
        return markRepository.save(markToSave);
    }

    public List<MarkDto> getUserMarks(String name) {
        User user = userService.getUserByEmail(name);
        if (user.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals(
                Role.STUDENT.getName()))) {
            return user.getMarks().stream().map(MarkDto::new).collect(
                    Collectors.toList());
        }
        return new ArrayList<MarkDto>();
    }
}
