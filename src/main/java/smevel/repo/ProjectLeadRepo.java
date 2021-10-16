package smevel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smevel.entity.ProjectLead;

import java.util.Collection;
import java.util.UUID;

public interface ProjectLeadRepo extends JpaRepository<ProjectLead, UUID> {
    @Query("SELECT pl from ProjectLead pl  WHERE pl.project.projectId = ?1 ")
    Collection<ProjectLead> findByProjectId(UUID projectId);

    @Query("SELECT pl from ProjectLead pl  WHERE pl.project.projectId = ?1 or pl.employee.employeeId = ?2")
    Collection<ProjectLead> findByProjectIdOrEmployeeId(UUID projectId, UUID employeeId);
}
