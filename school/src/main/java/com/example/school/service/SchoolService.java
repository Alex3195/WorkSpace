package com.example.school.service;

import com.example.school.dto.FullSchoolResponseDTO;
import com.example.school.entity.SchoolEntity;

import java.util.List;

public interface SchoolService {
    void saveSchool(SchoolEntity e);
    List<SchoolEntity> getListOfSchools();

    FullSchoolResponseDTO getSchoolWithStudentsById(Long schoolId);
}
