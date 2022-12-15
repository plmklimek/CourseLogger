package com.example.demo.models.dtos;

import com.example.demo.models.Course;
import com.example.demo.models.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    private User teacher;

    private User student;

    private Course courses;

    private Double mark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkDto markDto = (MarkDto) o;
        return Objects.equals(id, markDto.id) && Objects.equals(teacher,
                markDto.teacher) && Objects.equals(student,
                markDto.student) && Objects.equals(courses,
                markDto.courses) && Objects.equals(mark, markDto.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacher, student, courses, mark);
    }

    @Override
    public String toString() {
        return "MarkDto{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", student=" + student +
                ", courses=" + courses +
                ", mark=" + mark +
                '}';
    }
}
