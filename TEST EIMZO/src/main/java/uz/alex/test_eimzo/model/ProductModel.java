package uz.alex.test_eimzo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = {"product_name"})})
public class ProductModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_group_id")
    private Integer productGroupId;
    @Column(name = "product_group")
    private String productGroup;
    @Column(name = "brand")
    private String brand;
    @Column(name = "created")
    @CreationTimestamp
    private Date created;
    @Column(name = "modified")
    @CreationTimestamp
    private Date modified;
}
