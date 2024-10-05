package uz.alex.workspace.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.alex.workspace.constants.DataStatusEnum;
import uz.alex.workspace.entity.Department;
import uz.alex.workspace.form.DataTableForm;
import uz.alex.workspace.form.FilterForm;
import uz.alex.workspace.model.DepartmentModel;
import uz.alex.workspace.repositories.DepartmentRepository;
import uz.alex.workspace.service.DepartmentService;

import java.util.Date;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataTableForm getAllDepartments(FilterForm filter) {
        PageRequest page = PageRequest.of(filter.getStart() / filter.getLength(), filter.getLength());
        Specification<Department> spec = null;
        String search = filter.getSearch().getValue();
        if (search != null && !search.isEmpty()) {
            spec = Specification.where(searchDataByName(search));
        }
        assert spec != null;
        Page<Department> aicModels = departmentRepository.findAll(spec, page);
        long count = departmentRepository.count(spec);
        DataTableForm dataTablesForm = new DataTableForm();
        dataTablesForm.setDraw(filter.getDraw());
        dataTablesForm.setRecordsTotal((int) count);
        dataTablesForm.setRecordsFiltered((int) count);
        dataTablesForm.setData(aicModels.map(x -> modelMapper.map(x, DepartmentModel.class)).stream().toList());
        return dataTablesForm;
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
            department.setDataStatus(DataStatusEnum.UPDATED.name());
        } else {
            department.setDataStatus(DataStatusEnum.CREATED.name());
        }
        return department;
    }

    private Specification<Department> searchDataByName(String search) {
        return (Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (search == null) {
                return criteriaBuilder.conjunction(); // No filtering if name is null
            }
            return criteriaBuilder.like(root.get("name"), "%" + search + "%");
        };
    }
}
