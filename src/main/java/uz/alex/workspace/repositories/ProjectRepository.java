package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.workspace.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
