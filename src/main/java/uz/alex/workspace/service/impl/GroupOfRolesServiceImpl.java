package uz.alex.workspace.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.alex.workspace.entity.GroupOfRoles;
import uz.alex.workspace.model.GroupOfRolesModel;
import uz.alex.workspace.repositories.GroupOfRolesRepository;
import uz.alex.workspace.service.GroupOfRolesService;

import java.util.List;
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
    public List<GroupOfRolesModel> getAllGroupOfRoles() {
        List<GroupOfRoles> groupOfRoles = repository.findAll();
        return groupOfRoles.stream().map(x -> modelMapper.map(x, GroupOfRolesModel.class)).toList();
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
}
