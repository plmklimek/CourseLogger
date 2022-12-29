package com.example.demo.controllers;

import com.example.demo.models.ErrorModal;
import com.example.demo.models.dtos.CourseDto;
import com.example.demo.models.dtos.MarkCreate;
import com.example.demo.models.dtos.MarkDto;
import com.example.demo.models.dtos.users.UserDto;
import com.example.demo.models.enums.Role;
import com.example.demo.services.MarksService;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/marks")
public class MarksController {
    private final MarksService marksService;

    private final UserService userService;

    @GetMapping("/byUser/{id}")
    public ResponseEntity<Map<CourseDto, List<MarkDto>>> getMarksByUser(
            @PathVariable Long id) {
        return ResponseEntity.ok(marksService.findByUserId(id));
    }

    @GetMapping("/byCourse/{id}")
    public ResponseEntity<Map<UserDto, List<MarkDto>>> getMarksByCourse(
            @PathVariable Long id) {
        return ResponseEntity.ok(marksService.findByCourseId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMark(@RequestBody MarkCreate mark,
                                        Principal principal) {
        try {
            if (userService.hasPermission(principal.getName(), Role.TEACHER))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(marksService.createMark(mark));
            return null;
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(new ErrorModal(exception.getMessage()));
        }
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMarks(Principal principal) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(marksService.getUserMarks(principal.getName()));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest()
                    .body(new ErrorModal(exception.getMessage()));
        }
    }
}
