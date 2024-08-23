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
public class AICModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    @Column(name = "code",unique = true)
    private String code;
    private String packageType;
    private String productGroup;
    private Integer productGroupId;
    private String producedDate;
    private String ownerInn;
    private String ownerName;
    private String producerName;
    private String producerInn;

    @Column(name = "created")
    @CreationTimestamp
    private Date created;
    @Column(name = "modified")
    @CreationTimestamp
    private Date modified;

}
