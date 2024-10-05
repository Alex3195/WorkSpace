package uz.alex.workspace.service.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.alex.workspace.entity.GroupOfRoles;
import uz.alex.workspace.form.DataTableForm;
import uz.alex.workspace.form.FilterForm;
import uz.alex.workspace.model.GroupOfRolesModel;
import uz.alex.workspace.repositories.GroupOfRolesRepository;
import uz.alex.workspace.service.GroupOfRolesService;

import java.util.Optional;

@Service
public class GroupOfRolesServiceImpl implements GroupOfRolesService {
    private final GroupOfRolesRepository repository;
    private final ModelMapper modelMapper;

    public GroupOfRolesServiceImpl(GroupOfRolesRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GroupOfRolesModel getGroupOfRolesById(Integer id) {
        Optional<GroupOfRoles> groupOfRoles = repository.findById(id);
        return groupOfRoles.map(ofRoles -> modelMapper.map(ofRoles, GroupOfRolesModel.class)).orElse(null);
    }

    @Override
    public DataTableForm getAllGroupOfRoles(FilterForm filter) {
        PageRequest page = PageRequest.of(filter.getStart() / filter.getLength(), filter.getLength());
        Specification<GroupOfRoles> spec = null;
        String search = filter.getSearch().getValue();
        if (search != null && !search.isEmpty()) {
            spec = Specification.where(searchDataByName(search));
        }
        assert spec != null;
        Page<GroupOfRoles> aicModels = repository.findAll(spec, page);
        long count = repository.count(spec);
        DataTableForm dataTablesForm = new DataTableForm();
        dataTablesForm.setDraw(filter.getDraw());
        dataTablesForm.setRecordsTotal((int) count);
        dataTablesForm.setRecordsFiltered((int) count);
        dataTablesForm.setData(aicModels.map(x -> modelMapper.map(x, GroupOfRolesModel.class)).stream().toList());
        return dataTablesForm;

    }

    @Override
    public GroupOfRolesModel saveGroupOfRoles(GroupOfRolesModel model) {
        GroupOfRoles groupOfRoles = modelMapper.map(model, GroupOfRoles.class);
        groupOfRoles.setStatus("CREATED");
        return modelMapper.map(repository.save(groupOfRoles), GroupOfRolesModel.class);
    }

    @Override
    public GroupOfRolesModel updateGroupOfRoles(GroupOfRolesModel model) {
        Optional<GroupOfRoles> groupOfRoles = repository.findById(model.getId());
        if (groupOfRoles.isPresent()) {
            groupOfRoles.get().setName(model.getName());
            groupOfRoles.get().setDescription(model.getDescription());
            groupOfRoles.get().setStatus("UPDATED");
            return modelMapper.map(repository.save(groupOfRoles.get()), GroupOfRolesModel.class);
        }
        return null;
    }

    @Override
    public void deleteGroupOfRoles(Integer id) {
        Optional<GroupOfRoles> groupOfRoles = repository.findById(id);
        if (groupOfRoles.isPresent()) {
            groupOfRoles.get().setStatus("DELETED");
            repository.save(groupOfRoles.get());
        }
    }

    private Specification<GroupOfRoles> searchDataByName(String search) {
        return (Root<GroupOfRoles> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (search == null) {
                return criteriaBuilder.conjunction(); // No filtering if name is null
            }
            return criteriaBuilder.like(root.get("name"), "%" + search + "%");
        };
    }

}
