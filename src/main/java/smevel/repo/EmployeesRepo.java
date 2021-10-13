package smevel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import smevel.entity.Employee;

import java.util.UUID;

public interface EmployeesRepo extends JpaRepository<Employee, UUID> {
}
