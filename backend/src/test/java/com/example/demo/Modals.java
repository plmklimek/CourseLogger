package com.example.demo;

import com.example.demo.models.Authority;
import com.example.demo.models.Course;
import com.example.demo.models.Mark;
import com.example.demo.models.StudentCourse;
import com.example.demo.models.User;
import com.example.demo.models.enums.Role;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Modals {
    public static Authority getTeacherAuthority() {
        return Authority.builder().authority(Role.TEACHER.getName()).build();
    }

    public static Authority getStudentAuthority() {
        return Authority.builder().authority(Role.STUDENT.getName()).build();
    }

    public static Authority getAdminAuthority() {
        return Authority.builder().authority(Role.ADMIN.getName()).build();
    }

    public static Course getCourse() {
        return Course.builder().id(1L)
                .name("Przyroda")
                .build();
    }

    public static StudentCourse getStudentCourse() {
        return StudentCourse.builder().id(1L)
                .course(getCourse())
                .build();
    }

    public static Mark getMark() {
        return Mark.builder()
                .id(1L)
                .mark(4.0)
                .course(getCourse())
                .build();
    }

    public static User getStudent() {
        return User.builder()
                .id(1L)
                .email("mail@mail.com")
                .password("test")
                .authorities(
                        new HashSet<>(Collections.singletonList(getStudentAuthority())))
                .courses(Collections.singletonList(getStudentCourse()))
                .conductedCourses(new ArrayList<>())
                .marks(Collections.singletonList(getMark()))
                .build();
    }

    public static User getTeacher() {
        return User.builder()
                .id(2L)
                .email("mail@mail.com")
                .password("test")
                .authorities(
                        new HashSet<>(Collections.singletonList(getTeacherAuthority())))
                .courses(new ArrayList<>())
                .conductedCourses(Collections.singletonList(getCourse()))
                .marks(new ArrayList<>())
                .build();
    }

    public static User getAdmin() {
        return User.builder()
                .id(3L)
                .email("mail@mail.com")
                .password("test")
                .authorities(
                        new HashSet<>(Collections.singletonList(getAdminAuthority())))
                .courses(new ArrayList<>())
                .conductedCourses(new ArrayList<>())
                .marks(new ArrayList<>())
                .build();
    }
    public static MultipartFile getMultipartFile() {
        return new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
    }
}
