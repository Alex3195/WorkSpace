package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.alex.workspace.entity.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query(value = "from Department e where e.dataStatus is null or e.dataStatus <> 'DELETED'")
    List<Department> findAllDeletedDepartments();
}
