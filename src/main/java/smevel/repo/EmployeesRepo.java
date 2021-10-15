package smevel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import smevel.entity.Employee;
import smevel.entity.Project;

import java.util.Collection;
import java.util.UUID;

public interface EmployeesRepo extends JpaRepository<Employee, UUID> {

    Collection<Employee> findByProject(Project project);
}
