package com.example.demo.models.dtos.users;

import com.example.demo.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserDto {

    public Long id;

    public String name;
    public String surname;
    public String email;

    public UserDto(Long id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public UserDto(User user) {
        this(user.getId(), user.getName(), user.getSurname(),
                user.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(name,
                userDto.name) && Objects.equals(surname,
                userDto.surname) && Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
