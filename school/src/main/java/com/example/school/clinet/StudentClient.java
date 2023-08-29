package com.example.school.clinet;

import com.example.school.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "student-service", url = "${application.config.students-url}")
public interface StudentClient {
    @GetMapping("/school/{id}")
    List<StudentDTO> findAllStudentsBySchoolId(@PathVariable("id") Long schoolId);
}
