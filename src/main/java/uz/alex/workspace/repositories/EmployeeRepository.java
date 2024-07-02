package uz.alex.workspace.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.workspace.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
