package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateCourse {
    private Long id;
    private String name;
    private Long teacherId;
    private Long studentId;
}
