package com.example.school.service.impl;

import com.example.school.clinet.StudentClient;
import com.example.school.dto.FullSchoolResponseDTO;
import com.example.school.entity.SchoolEntity;
import com.example.school.repository.SchoolRepository;
import com.example.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final StudentClient client;

    @Override
    public void saveSchool(SchoolEntity e) {
        schoolRepository.save(e);
    }

    @Override
    public List<SchoolEntity> getListOfSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public FullSchoolResponseDTO getSchoolWithStudentsById(Long schoolId) {
        var school = schoolRepository.findById(schoolId).orElse(null);
        var students = client.findAllStudentsBySchoolId(schoolId);
        return FullSchoolResponseDTO.builder()
                .name(school.getName()).
                email(school.getEmail()).students(students).build();
    }
}
