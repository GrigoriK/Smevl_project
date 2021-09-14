package sprinExamples.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@Table(name = "positions")
public class Position implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID positionId;
    private String positionName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Collection<Employee> employees;

}
