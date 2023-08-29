package com.example.students.controller;

import com.example.students.entities.StudentEntity;
import com.example.students.service.StudentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody StudentEntity entity) {
        studentService.save(entity);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentEntity>> getListOFStudents() {
        return ResponseEntity.of(Optional.ofNullable(studentService.listOfStudents()));
    }

    @GetMapping("/school/{id}")
    public ResponseEntity<List<StudentEntity>> getListOfStudentsAtTheSchool(@PathVariable("id") Long schoolId) {
        return ResponseEntity.of(Optional.ofNullable(studentService.findStudentsBySchoolId(schoolId)));
    }
}
