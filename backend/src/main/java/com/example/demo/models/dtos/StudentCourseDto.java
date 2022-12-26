package com.example.demo.models.dtos;

import com.example.demo.models.Course;
import com.example.demo.models.User;
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
public class StudentCourseDto {
    private Long id;

    private User student;

    private Course course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseDto that = (StudentCourseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(student,
                that.student) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, course);
    }

    @Override
    public String toString() {
        return "StudentCourseDto{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
