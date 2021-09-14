package sprinExamples.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sprinExamples.entity.Project;

import java.util.Collection;
import java.util.UUID;

public interface ProjectsRepo extends JpaRepository<Project, UUID> {

    Collection<Project> findByProjectName(String projectName);

    Collection<Project> findByProjectCode(String projectCode);
}
