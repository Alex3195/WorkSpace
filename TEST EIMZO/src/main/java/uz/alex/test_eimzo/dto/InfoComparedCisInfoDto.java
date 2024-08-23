package uz.alex.test_eimzo.dto;

import lombok.Data;

import java.util.List;

@Data
public class InfoComparedCisInfoDto {
    private String cis;
    private Integer numberOfCMFromAslBelgi;
    private List<String> childFromAslBelgi;
    private Integer numberOfCMFromUploadedData;
    private List<String> childFromUploadedData;
    private Boolean status;
    private String aicDtoId;

}
