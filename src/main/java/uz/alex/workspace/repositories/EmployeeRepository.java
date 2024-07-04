package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.alex.workspace.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query(value = "from Employee e where e.dataStatus is null or e.dataStatus <> 'DELETED'")
    List<Employee> findAllEmployees();
}
