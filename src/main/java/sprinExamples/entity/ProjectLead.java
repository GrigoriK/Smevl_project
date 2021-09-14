package sprinExamples.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "project_leads")
public class ProjectLead implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID projectLeadId;

    @OneToOne()
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @OneToOne()
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Project project;
}
