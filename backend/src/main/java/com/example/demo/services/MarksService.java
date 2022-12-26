package com.example.demo.services;

import com.example.demo.models.dtos.CourseDto;
import com.example.demo.models.dtos.MarkDto;
import com.example.demo.models.dtos.users.UserDto;
import com.example.demo.repositories.MarkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MarksService {
    private final MarkRepository markRepository;

    public Map<CourseDto, List<MarkDto>> findByUserId(Long id) {
        return markRepository.findByUserId(id).stream().map(MarkDto::new)
                .collect(Collectors.groupingBy(MarkDto::getCourse));
    }

    public Map<UserDto, List<MarkDto>> findByCourseId(Long id) {
        return markRepository.findByCourseId(id).stream().map(MarkDto::new)
                .collect(Collectors.groupingBy(markDto -> markDto.getStudent()));
    }
}
