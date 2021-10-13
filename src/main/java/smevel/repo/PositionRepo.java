package smevel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import smevel.entity.Position;

import java.util.Collection;
import java.util.UUID;

public interface PositionRepo extends JpaRepository<Position, UUID> {

    Collection<Position> findByPositionName(String positionName);
}
