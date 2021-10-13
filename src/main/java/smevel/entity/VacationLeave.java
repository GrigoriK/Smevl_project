package smevel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "vacations")
public class VacationLeave implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vacation_id")
    private UUID vacationId;

    @Column(name = "vacation_start_date")
    private Date vacationStartDate;

    @Column(name = "vacation_end_date")
    private Date vacationEndDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
