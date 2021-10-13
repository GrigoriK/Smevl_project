package smevel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "projects")
public class Project implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private UUID projectId;
    private String projectName;
    private String projectCode;

    @OneToOne(mappedBy = "project")
    private ProjectLead projectLead;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Collection<Employee> employees;
}
