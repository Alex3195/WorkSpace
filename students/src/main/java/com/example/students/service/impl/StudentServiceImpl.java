package com.example.students.service.impl;

import com.example.students.entities.StudentEntity;
import com.example.students.repository.StudentRepository;
import com.example.students.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public void save(StudentEntity studentEntity) {
        studentRepository.save(studentEntity);
    }

    @Override
    public List<StudentEntity> listOfStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentEntity> findStudentsBySchoolId(Long schoolId) {
        return studentRepository.findAllBySchoolId(schoolId);
    }
}

