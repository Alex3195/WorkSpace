package uz.alex.workspace.service.impl;

import org.springframework.stereotype.Service;
import uz.alex.workspace.constants.DataStatusEnum;
import uz.alex.workspace.entity.Department;
import uz.alex.workspace.model.DepartmentModel;
import uz.alex.workspace.repositories.DepartmentRepository;
import uz.alex.workspace.service.DepartmentService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentModel> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::departmentEntityToModel).toList();
    }

    @Override
    public DepartmentModel getDepartmentById(int id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(this::departmentEntityToModel).orElse(null);
    }

    @Override
    public DepartmentModel createDepartment(DepartmentModel department) {
        Department entity = departmentModelToEntity(department);
        entity = departmentRepository.save(entity);
        return departmentEntityToModel(entity);
    }

    @Override
    public DepartmentModel updateDepartment(DepartmentModel department) {
        Department entity = departmentModelToEntity(department);
        entity = departmentRepository.save(entity);
        return departmentEntityToModel(entity);
    }

    @Override
    public void deleteDepartment(int id) {
        Optional<Department> department = departmentRepository.findById(id);
        department.ifPresent(x -> {
            x.setDataStatus(DataStatusEnum.DELETED.name());
            x.setUpdatedAt(new Date());
            departmentRepository.save(x);
        });

    }

    private DepartmentModel departmentEntityToModel(Department department) {
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setId(department.getId());
        departmentModel.setName(department.getName());
        departmentModel.setDescription(department.getDescription());
        return departmentModel;
    }

    private Department departmentModelToEntity(DepartmentModel departmentModel) {
        Department department = new Department();
        department.setId(departmentModel.getId());
        department.setName(departmentModel.getName());
        department.setDescription(departmentModel.getDescription());
        if (departmentModel.getId() != null) {
            department.setUpdatedAt(new Date());
            department.setDataStatus(DataStatusEnum.UPDATED.name());
        } else {
            department.setCreatedAt(new Date());
            department.setDataStatus(DataStatusEnum.CREATED.name());
        }
        return department;
    }
}
