package com.example.demo.models.dtos.users;

import com.example.demo.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String image;

    private String password;

    public UserDto(Long id, String name, String surname, String email, String image) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.image = image;
    }

    public UserDto(User user) {
        this(user.getId(), user.getName(), user.getSurname(), user.getEmail(),
                user.getImage());
    }

    public User getUser() {
        return new User(this.id, this.name, this.surname, this.email, null,
                new HashSet<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), this.image);
    }

    public User getUserWithPassword() {
        return new User(this.id, this.name, this.surname, this.email, this.password,
                new HashSet<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), this.image);
    }

    public UserDto loadPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(name,
                userDto.name) && Objects.equals(surname,
                userDto.surname) && Objects.equals(email,
                userDto.email) && Objects.equals(image, userDto.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, image);
    }

    @Override
    public String toString() {
        return "UserDto{" + "id=" + id + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", email='" + email + '\'' + ", image='" + image + '\'' + '}';
    }
}
