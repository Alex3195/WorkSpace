package uz.alex.test_eimzo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductInfoResponse {
    private CisInfo cisInfo;

    @Data
    public static class CisInfo {
        private String id;
        private String requestedCis;
        private String cis;
        private String gtin;
        private String tnVedEaes;
        private String tnVedEaesGroup;
        private String productName;
        private int productGroupId;
        private String productGroup;
        private String brand;
        private String producedDate;
        private String emissionDate;
        private String emissionType;
        private String packageType;
        private String ownerInn;
        private String ownerName;
        private String status;
        private String statusEx;
        private List<String> child;
        private String parent;
        private String producerInn;
        private String producerName;
        private String prVetDocument;
        private String exporterName;
        private String productionDate;
        private String expirationDate;
        private String productionSerialNumber;

    }
}
