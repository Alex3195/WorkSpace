package uz.alex.test_eimzo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
public class MarkingCodeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @Column(name = "created")
    @CreationTimestamp
    private Date created;
    @Column(name = "modified")
    @CreationTimestamp
    private Date modified;
}
