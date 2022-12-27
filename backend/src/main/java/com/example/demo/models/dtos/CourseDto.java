package com.example.demo.models.dtos;

import com.example.demo.models.Course;
import com.example.demo.models.StudentCourse;
import com.example.demo.models.User;
import com.example.demo.models.dtos.users.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDto {
    private Long id;

    private String name;

    private List<UserDto> students = new ArrayList<>();

    private UserDto teacher;

    public CourseDto(Course course) {
        this(course.getId(), course.getName(), null, null);
    }

    public CourseDto loadDetails(List<StudentCourse> students, User teacher) {
        this.students =
                students.stream()
                        .map(studentCourse -> new UserDto(studentCourse.getStudent()))
                        .collect(
                                Collectors.toList());
        this.teacher = new UserDto(teacher);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDto courseDto = (CourseDto) o;
        return Objects.equals(id, courseDto.id) && Objects.equals(name,
                courseDto.name) && Objects.equals(students,
                courseDto.students) && Objects.equals(teacher, courseDto.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, students, teacher);
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                ", teacher=" + teacher +
                '}';
    }
}
