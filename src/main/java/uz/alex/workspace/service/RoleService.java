package uz.alex.workspace.service;

import uz.alex.workspace.model.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> getRoles();

    RoleModel getRole(int id);

    RoleModel createRole(RoleModel role);

    RoleModel updateRole(RoleModel role);

    void deleteRole(int id);
}
