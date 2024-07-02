package uz.alex.workspace.service.impl;

import org.springframework.stereotype.Service;
import uz.alex.workspace.constants.DataStatusEnum;
import uz.alex.workspace.entity.Employee;
import uz.alex.workspace.model.EmployeeModel;
import uz.alex.workspace.repositories.EmployeeRepository;
import uz.alex.workspace.service.EmployeeService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeModel> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(this::employeeToModel).toList();
    }

    @Override
    public EmployeeModel getEmployee(int id) {
        return employeeRepository.findById(id).map(this::employeeToModel).orElse(null);
    }

    @Override
    public EmployeeModel createEmployee(EmployeeModel employee) {
        Employee e = employeeModelToEmployee(employee);
        e = employeeRepository.save(e);
        return employeeToModel(e);
    }

    @Override
    public EmployeeModel updateEmployee(EmployeeModel employee) {
        Employee e = employeeModelToEmployee(employee);
        e = employeeRepository.save(e);
        return employeeToModel(e);
    }

    @Override
    public void deleteEmployee(int id) {
        Optional<Employee> e = employeeRepository.findById(id);
        e.ifPresent(x -> {
            x.setDataStatus(DataStatusEnum.DELETED.name());
            x.setUpdatedAt(new Date());
            employeeRepository.save(x);
        });
    }

    private EmployeeModel employeeToModel(Employee employee) {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setId(employee.getId());
        employeeModel.setFirstName(employee.getFirstName());
        employeeModel.setLastName(employee.getLastName());
        employeeModel.setEmail(employee.getEmail());
        employeeModel.setPhone(employee.getPhone());
        employeeModel.setPosition(employee.getPosition());
        return employeeModel;
    }

    private Employee employeeModelToEmployee(EmployeeModel employeeModel) {
        Employee employee = new Employee();
        employee.setId(employeeModel.getId());
        employee.setFirstName(employeeModel.getFirstName());
        employee.setLastName(employeeModel.getLastName());
        employee.setEmail(employeeModel.getEmail());
        employee.setPhone(employeeModel.getPhone());
        employee.setPosition(employeeModel.getPosition());

        if (employeeModel.getId() != null) {
            employee.setDataStatus(DataStatusEnum.UPDATED.name());
            employee.setUpdatedAt(new Date());
        } else {
            employee.setUpdatedAt(new Date());
            employee.setDataStatus(DataStatusEnum.CREATED.name());
        }
        return employee;
    }
}
