package uz.alex.test_eimzo.dto;

import lombok.Data;

@Data
public class MarkingCodeDto {
    private String uuid;
    private String aicUUID;
    private String code;
    private String gtin;
    private String producedDate;
    private String packageType;
    private String parent;
    private String productionDate;
    private String expirationDate;
    private String productionSerialNumber;
    private String productUUID;
}
