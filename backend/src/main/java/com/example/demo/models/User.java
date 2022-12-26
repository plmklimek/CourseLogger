package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"student", "teacher"})
    private List<StudentCourse> courses = new ArrayList<StudentCourse>();

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"student", "teacher", "course"})
    private List<Course> conductedCourses = new ArrayList<Course>();
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties({"student", "teacher"})
    private List<Mark> marks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name,
                user.name) && Objects.equals(surname,
                user.surname) && Objects.equals(email,
                user.email) && Objects.equals(password,
                user.password) && Objects.equals(authorities,
                user.authorities) && Objects.equals(courses,
                user.courses) && Objects.equals(marks, user.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, authorities, courses,
                marks);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                ", students=" + courses +
                ", marks=" + marks +
                '}';
    }
}
