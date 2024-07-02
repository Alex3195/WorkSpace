package uz.alex.workspace.service;

import uz.alex.workspace.model.DepartmentModel;

import java.util.List;

public interface DepartmentService {
    List<DepartmentModel> getAllDepartments();
    DepartmentModel getDepartmentById(int id);
    DepartmentModel createDepartment(DepartmentModel department);
    DepartmentModel updateDepartment(DepartmentModel department);
    void deleteDepartment(int id);

}
