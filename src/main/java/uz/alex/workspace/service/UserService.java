package uz.alex.workspace.service;

import uz.alex.workspace.model.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> getAllUsers();

    UserModel getUserById(Long id);

    UserModel createUser(UserModel user);

    UserModel updateUser(UserModel user);

    void deleteUser(Long id);
}
