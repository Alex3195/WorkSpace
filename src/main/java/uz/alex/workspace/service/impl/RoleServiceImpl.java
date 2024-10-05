package uz.alex.workspace.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.alex.workspace.entity.Roles;
import uz.alex.workspace.form.DataTableForm;
import uz.alex.workspace.form.FilterForm;
import uz.alex.workspace.model.RoleModel;
import uz.alex.workspace.repositories.RoleRepository;
import uz.alex.workspace.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataTableForm getRoles(FilterForm filter) {
        PageRequest page = PageRequest.of(filter.getStart() / filter.getLength(), filter.getLength());
        Specification<Roles> spec = null;
        String search = filter.getSearch().getValue();
        if (search != null && !search.isEmpty()) {
            spec = Specification.where(searchDataByName(search));
        }
        assert spec != null;
        Page<Roles> roleModels = roleRepository.findAll(spec, page);
        long count = roleRepository.count(spec);
        DataTableForm dataTablesForm = new DataTableForm();
        dataTablesForm.setDraw(filter.getDraw());
        dataTablesForm.setRecordsTotal((int) count);
        dataTablesForm.setRecordsFiltered((int) count);
        dataTablesForm.setData(roleModels.map(x -> modelMapper.map(x, RoleModel.class)).stream().toList());
        return dataTablesForm;
    }

    @Override
    public RoleModel getRole(int id) {
        return roleRepository.findById(id).map(item -> modelMapper.map(item, RoleModel.class)).orElse(null);
    }

    @Override
    public RoleModel createRole(RoleModel role) {
        return modelMapper.map(roleRepository.save(modelMapper.map(role, Roles.class)), RoleModel.class);
    }

    @Override
    public RoleModel updateRole(RoleModel role) {
        return modelMapper.map(roleRepository.save(modelMapper.map(role, Roles.class)), RoleModel.class);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }


    private Specification<Roles> searchDataByName(String search) {
        return (Root<Roles> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (search == null) {
                return criteriaBuilder.conjunction(); // No filtering if name is null
            }
            return criteriaBuilder.like(root.get("name"), "%" + search + "%");
        };
    }
}
