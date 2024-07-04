package uz.alex.workspace.repositories;

import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.alex.workspace.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query("from Project e where e.dataStatus <> 'DELETED'")
    List<Project> findAllActiveProjects();
}
