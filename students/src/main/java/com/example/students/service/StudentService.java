package com.example.students.service;

import com.example.students.entities.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void save(StudentEntity studentEntity);
    List<StudentEntity> listOfStudents();

    List<StudentEntity> findStudentsBySchoolId(Long schoolId);
}
