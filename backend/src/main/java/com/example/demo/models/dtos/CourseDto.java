package com.example.demo.models.dtos;

import com.example.demo.models.Course;
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
public class CourseDto {
    private Long id;

    private String name;

    public CourseDto(Course course) {
        this(course.getId(), course.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDto courseDto = (CourseDto) o;
        return Objects.equals(id, courseDto.id) && Objects.equals(name,
                courseDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
