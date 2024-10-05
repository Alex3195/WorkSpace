package uz.alex.workspace.service;

import uz.alex.workspace.form.DataTableForm;
import uz.alex.workspace.form.FilterForm;
import uz.alex.workspace.model.GroupOfRolesModel;

public interface GroupOfRolesService {
    GroupOfRolesModel getGroupOfRolesById(Integer id);

    DataTableForm getAllGroupOfRoles(FilterForm filter);

    GroupOfRolesModel saveGroupOfRoles(GroupOfRolesModel model);

    GroupOfRolesModel updateGroupOfRoles(GroupOfRolesModel model);

    void deleteGroupOfRoles(Integer id);
}
