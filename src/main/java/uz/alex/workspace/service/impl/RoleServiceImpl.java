package uz.alex.workspace.service.impl;

import org.springframework.stereotype.Service;
import uz.alex.workspace.entity.Roles;
import uz.alex.workspace.model.RoleModel;
import uz.alex.workspace.repositories.RoleRepository;
import uz.alex.workspace.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleModel> getRoles() {
        return roleRepository.findAll().stream().map(this::entityToModel).toList();
    }

    @Override
    public RoleModel getRole(int id) {
        return roleRepository.findById(id).map(this::entityToModel).orElse(null);
    }

    @Override
    public RoleModel createRole(RoleModel role) {
        return entityToModel(roleRepository.save(modelToRole(role)));
    }

    @Override
    public RoleModel updateRole(RoleModel role) {
        return entityToModel(roleRepository.save(modelToRole(role)));
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    private RoleModel entityToModel(Roles role) {
        RoleModel roleModel = new RoleModel();
        roleModel.setId(role.getId());
        roleModel.setName(role.getName());
        roleModel.setDescription(role.getDescription());
        return roleModel;
    }

    private Roles modelToRole(RoleModel roleModel) {
        Roles role = new Roles();
        role.setId(roleModel.getId());
        role.setName(roleModel.getName());
        role.setDescription(roleModel.getDescription());
        return role;
    }
}
