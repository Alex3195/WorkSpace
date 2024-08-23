package uz.alex.test_eimzo.dto;

import lombok.Data;

@Data
public class AICDto {
    private String uuid;
    private String code;
    private String packageType;
    private String productGroup;
    private Integer productGroupId;
    private String producedDate;
    private String ownerInn;
    private String ownerName;
    private String producerName;
    private String producerInn;
}
