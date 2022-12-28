package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MarkCreate {
    private Double mark;
    private Long studentId;
    private Long teacherId;
    private Long courseId;
}
