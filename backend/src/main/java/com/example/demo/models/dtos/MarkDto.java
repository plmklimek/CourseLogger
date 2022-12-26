package com.example.demo.models.dtos;

import com.example.demo.models.Mark;
import com.example.demo.models.dtos.users.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MarkDto {
    private Long id;

    private UserDto teacher;

    private UserDto student;

    private CourseDto course;

    private Double mark;

    public MarkDto(Mark mark) {
        this(mark.getId(), new UserDto(mark.getTeacher()),
                new UserDto(mark.getStudent()), new CourseDto(mark.getCourse()),
                mark.getMark());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkDto markDto = (MarkDto) o;
        return Objects.equals(id, markDto.id) && Objects.equals(teacher,
                markDto.teacher) && Objects.equals(student,
                markDto.student) && Objects.equals(course,
                markDto.course) && Objects.equals(mark, markDto.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacher, student, course, mark);
    }

    @Override
    public String toString() {
        return "MarkDto{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", student=" + student +
                ", courses=" + course +
                ", mark=" + mark +
                '}';
    }
}
