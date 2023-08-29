package com.example.school.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullSchoolResponseDTO {
    private String name;
    private String email;
    private List<StudentDTO> students;
}
