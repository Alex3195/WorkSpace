package uz.alex.workspace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "positions")
@Setter
@Getter
public class Positions {
    @Id
    @GeneratedValue(generator = "position_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "positions_seq", sequenceName = "positions_seq")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "priority")
    private Integer priority;
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
        Positions positions = (Positions) o;
        return getId() != null && Objects.equals(getId(), positions.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
