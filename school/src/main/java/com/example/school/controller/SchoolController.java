package com.example.school.controller;

import com.example.school.dto.FullSchoolResponseDTO;
import com.example.school.entity.SchoolEntity;
import com.example.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/school")
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping(value = {"", "/"})
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSchool(@RequestBody SchoolEntity e) {
        schoolService.saveSchool(e);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<SchoolEntity>> getListOfSchools() {
        return ResponseEntity.of(Optional.ofNullable(schoolService.getListOfSchools()));
    }

    @GetMapping(value = "/with-student/{id}")
    public ResponseEntity<FullSchoolResponseDTO> getListOfSchools(@PathVariable("id") Long schoolId) {
        return ResponseEntity.of(Optional.ofNullable(schoolService.getSchoolWithStudentsById(schoolId)));
    }
}
