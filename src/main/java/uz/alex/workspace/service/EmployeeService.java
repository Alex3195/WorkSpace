package uz.alex.workspace.service;

import uz.alex.workspace.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {
    List<EmployeeModel> getEmployees();

    EmployeeModel getEmployee(int id);

    EmployeeModel createEmployee(EmployeeModel employee);

    EmployeeModel updateEmployee(EmployeeModel employee);

    void deleteEmployee(int id);
}
