package com.example.demo;

import com.example.demo.models.Authority;
import com.example.demo.models.User;
import com.example.demo.models.dtos.users.UserDto;
import com.example.demo.models.enums.Role;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.AuthorityService;
import com.example.demo.services.FileStorageService;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private FileStorageService fileStorageService;
    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityService authorityService;

    @Test
    void createUser() {
        UserDto userDto = new UserDto(Modals.getStudent());
        userDto.setPassword(Modals.getStudent().getPassword());
        doReturn(Modals.getStudent()).when(userRepository).save(any());
        assertDoesNotThrow(() -> {
            userService.createUser(userDto,
                    Modals.getMultipartFile(), Role.STUDENT);
        });
    }

    @Test
    void hasPermissionWithException() {
        doReturn(Modals.getStudent()).when(userRepository).findByEmail(any());
        assertThatIllegalArgumentException()
                .isThrownBy(() -> userService.hasPermission(Modals.getStudent().getName(),
                        Role.TEACHER))
                .withMessage("USER HAS NOT PERMISSION");
    }

    @Test
    void hasPermissionWithoutException() {
        User user = Modals.getStudent();
        Authority authority = new Authority();
        authority.setAuthority(Role.STUDENT.getName());
        user.setAuthorities(new HashSet<>(List.of(authority)));
        doReturn(user).when(userRepository).findByEmail(any());
        assertTrue(() -> userService.hasPermission(Modals.getStudent().getName(),
                Role.STUDENT));
    }

    @Test
    void getStudentById(){
        User user = Modals.getStudent();
        Authority authority = new Authority();
        authority.setAuthority(Role.STUDENT.getName());
        user.setAuthorities(new HashSet<>(List.of(authority)));
        doReturn(Optional.of(user)).when(userRepository).findById(1L);
        assertDoesNotThrow(() -> {
            userService.getStudentByIdUser(1L);
        });
    }

    @Test
    void getStudentByIdWithException(){
        User user = Modals.getStudent();
        Authority authority = new Authority();
        authority.setAuthority(Role.ADMIN.getName());
        user.setAuthorities(new HashSet<>(List.of(authority)));
        doReturn(Optional.of(user)).when(userRepository).findById(1L);
        assertThatIllegalArgumentException()
                .isThrownBy(() ->  userService.getStudentByIdUser(1L))
                .withMessage("USER DOESNT EXISTS");
    }

    @Test
    void getTeacherById(){
        User user = Modals.getStudent();
        Authority authority = new Authority();
        authority.setAuthority(Role.TEACHER.getName());
        user.setAuthorities(new HashSet<>(List.of(authority)));
        doReturn(Optional.of(user)).when(userRepository).findById(1L);
        assertDoesNotThrow(() -> {
            userService.getTeacherById(1L);
        });
    }

    @Test
    void getTeacherByIdWithException(){
        User user = Modals.getStudent();
        Authority authority = new Authority();
        authority.setAuthority(Role.ADMIN.getName());
        user.setAuthorities(new HashSet<>(List.of(authority)));
        doReturn(Optional.of(user)).when(userRepository).findById(1L);
        assertThatIllegalArgumentException()
                .isThrownBy(() ->  userService.getTeacherByIdUser(1L))
                .withMessage("USER DOESNT EXISTS");
    }

    @Test
    void getUserByEmail(){
        User user = Modals.getStudent();
        Authority authority = new Authority();
        authority.setAuthority(Role.TEACHER.getName());
        user.setAuthorities(new HashSet<>(List.of(authority)));
        doReturn(user).when(userRepository).findByEmail(Modals.getStudent()
                .getEmail());
        assertDoesNotThrow(() -> {
            userService.getUserByEmail(user.getEmail());
        });
    }

    @Test
    void getAllStudents(){
        User user = Modals.getStudent();
        Authority authority = new Authority();
        authority.setAuthority(Role.TEACHER.getName());
        user.setAuthorities(new HashSet<>(List.of(authority)));

        User user2 = Modals.getStudent();
        Authority authority2 = new Authority();
        authority2.setAuthority(Role.STUDENT.getName());
        user2.setAuthorities(new HashSet<>(List.of(authority2)));

        List<User> allUsers = List.of(user, user2);
        List<User> studentUsers = List.of(user2);

        doReturn(allUsers).when(userRepository).findAll();
        assertEquals(userService.getAllStudents(),
                studentUsers.stream().map(UserDto::new).collect(Collectors.toList()));
    }

    @Test
    void getAllTeachers(){
        User user = Modals.getStudent();
        Authority authority = new Authority();
        authority.setAuthority(Role.TEACHER.getName());
        user.setAuthorities(new HashSet<>(List.of(authority)));

        User user2 = Modals.getStudent();
        Authority authority2 = new Authority();
        authority2.setAuthority(Role.STUDENT.getName());
        user2.setAuthorities(new HashSet<>(List.of(authority2)));

        List<User> allUsers = List.of(user, user2);
        List<User> teacherUsers = List.of(user);

        doReturn(allUsers).when(userRepository).findAll();
        assertEquals(userService.getAllStudents(),
                teacherUsers.stream().map(UserDto::new).collect(Collectors.toList()));
    }
}
