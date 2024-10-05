package uz.alex.workspace.service;

import uz.alex.workspace.form.DataTableForm;
import uz.alex.workspace.form.FilterForm;
import uz.alex.workspace.model.RoleModel;


public interface RoleService {
    DataTableForm getRoles(FilterForm filter);

    RoleModel getRole(int id);

    RoleModel createRole(RoleModel role);

    RoleModel updateRole(RoleModel role);

    void deleteRole(int id);
}
