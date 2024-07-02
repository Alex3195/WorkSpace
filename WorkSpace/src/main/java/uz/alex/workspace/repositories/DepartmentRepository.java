package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.workspace.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
