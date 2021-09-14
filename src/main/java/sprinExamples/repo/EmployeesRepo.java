package sprinExamples.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sprinExamples.entity.Employee;

import java.util.UUID;

public interface EmployeesRepo extends JpaRepository<Employee, UUID> {
}
