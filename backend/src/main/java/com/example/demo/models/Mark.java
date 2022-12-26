package com.example.demo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "marks")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private User teacher;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private User student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    private Double mark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark1 = (Mark) o;
        return Objects.equals(id, mark1.id) && Objects.equals(teacher,
                mark1.teacher) && Objects.equals(student,
                mark1.student) && Objects.equals(course,
                mark1.course) && Objects.equals(mark, mark1.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacher, student, course, mark);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", student=" + student +
                ", courses=" + course +
                ", mark=" + mark +
                '}';
    }
}
