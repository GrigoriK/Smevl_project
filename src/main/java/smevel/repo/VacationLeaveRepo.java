package smevel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import smevel.entity.VacationLeave;

import java.util.UUID;

public interface VacationLeaveRepo extends JpaRepository<VacationLeave, UUID> {
}
