package com.example.students.repository;

import com.example.students.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findAllBySchoolId(Long schoolId);
}
