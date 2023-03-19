package com.example.demo.models.dtos;

public class MarkCourseDto {
    Long studentId;
    Double mark;

    public MarkCourseDto(Long studentId, Double mark) {
        this.studentId = studentId;
        this.mark = mark;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}
