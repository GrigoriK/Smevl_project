package sprinExamples.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sprinExamples.entity.Employee;
import sprinExamples.entity.Position;
import sprinExamples.entity.Project;

import java.util.Collection;
import java.util.UUID;

public interface PositionRepo extends JpaRepository<Position, UUID> {

    Collection<Position> findByPositionName(String positionName);
}
