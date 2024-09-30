package uz.alex.workspace.service;

import uz.alex.workspace.model.GroupOfRolesModel;

import java.util.List;

public interface GroupOfRolesService {
    GroupOfRolesModel getGroupOfRolesById(Integer id);

    List<GroupOfRolesModel> getAllGroupOfRoles();

    GroupOfRolesModel saveGroupOfRoles(GroupOfRolesModel model);

    GroupOfRolesModel updateGroupOfRoles(GroupOfRolesModel model);

    void deleteGroupOfRoles(Integer id);
}
