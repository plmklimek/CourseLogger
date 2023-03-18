package com.example.demo.repositories;

import com.example.demo.models.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    @Query("FROM Mark m JOIN m.student student WHERE student.id = ?1")
    List<Mark> findByUserId(Long id);

    @Query("FROM Mark m JOIN m.course course WHERE course.id = ?1")
    List<Mark> findByCourseId(Long id);

    @Query("FROM Mark m JOIN m.course course JOIN m.student student WHERE course.id = ?1 AND student.id = ?2")
    List<Mark> findByCourseAndUserId(Long courseId, Long studentId);
}
