package com.example.demo.models.dtos;

import com.example.demo.models.Authority;
import com.example.demo.models.Course;
import com.example.demo.models.StudentCourse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDtoCopy {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private Set<Authority> authorities;

    private List<StudentCourse> courses = new ArrayList<StudentCourse>();

    private List<Course> conductedCourses = new ArrayList<Course>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDtoCopy userDto = (UserDtoCopy) o;
        return Objects.equals(id, userDto.id) && Objects.equals(name,
                userDto.name) && Objects.equals(surname,
                userDto.surname) && Objects.equals(email,
                userDto.email) && Objects.equals(authorities,
                userDto.authorities) && Objects.equals(courses,
                userDto.courses) && Objects.equals(conductedCourses,
                userDto.conductedCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, authorities, courses,
                conductedCourses);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", authorities=" + authorities +
                ", courses=" + courses +
                ", conductedCourses=" + conductedCourses +
                '}';
    }
}
