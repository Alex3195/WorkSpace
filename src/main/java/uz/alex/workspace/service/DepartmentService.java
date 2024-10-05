package uz.alex.workspace.service;

import uz.alex.workspace.form.DataTableForm;
import uz.alex.workspace.form.FilterForm;
import uz.alex.workspace.model.DepartmentModel;

public interface DepartmentService {
    DataTableForm getAllDepartments(FilterForm filter);
    DepartmentModel getDepartmentById(int id);
    DepartmentModel createDepartment(DepartmentModel department);
    DepartmentModel updateDepartment(DepartmentModel department);
    void deleteDepartment(int id);

}
