package uz.alex.workspace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "project")
@Getter
@Setter
@ToString
public class Project {
    @Id
    @GeneratedValue(generator = "project_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "project_seq", name = "project_seq")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "responsible_leader_id")
    private Integer responsibleLeaderId;
    @Column(name = "budget_sum")
    private BigDecimal budgetSum;
    @Column(name = "spent_sum")
    private BigDecimal spentSum;
    @Column(name = "residual_sum")
    private BigDecimal residualSum;
    @Column(name = "data_status")
    private String dataStatus;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Project project = (Project) o;
        return getId() != null && Objects.equals(getId(), project.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
