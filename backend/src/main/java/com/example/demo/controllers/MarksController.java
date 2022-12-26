package com.example.demo.controllers;

import com.example.demo.models.dtos.CourseDto;
import com.example.demo.models.dtos.MarkDto;
import com.example.demo.models.dtos.users.UserDto;
import com.example.demo.services.MarksService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/marks")
public class MarksController {
    private final MarksService marksService;

    @GetMapping("/byUser/{id}")
    public ResponseEntity<Map<CourseDto, List<MarkDto>>> getMarksByUser(
            @PathVariable Long id) {
        return ResponseEntity.ok(marksService.findByUserId(id));
    }

    @GetMapping("/byCourse/{id}")
    public ResponseEntity<Map<UserDto, List<MarkDto>>> getMarksByCouse(
            @PathVariable Long id) {
        return ResponseEntity.ok(marksService.findByCourseId(id));
    }

}
