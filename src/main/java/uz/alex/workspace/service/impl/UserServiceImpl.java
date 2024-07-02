package uz.alex.workspace.service.impl;

import org.springframework.stereotype.Service;
import uz.alex.workspace.constants.DataStatusEnum;
import uz.alex.workspace.entity.Users;
import uz.alex.workspace.model.UserModel;
import uz.alex.workspace.repositories.UserRepository;
import uz.alex.workspace.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<Users> users = userRepository.findAllActiveUsers();
        return users.stream().map(this::entityToModel).toList();
    }

    @Override
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).map(this::entityToModel).orElse(null);
    }

    @Override
    public UserModel createUser(UserModel user) {
        Users e = modelToEntity(user);
        return entityToModel(userRepository.save(e));
    }

    @Override
    public UserModel updateUser(UserModel user) {
        Users e = modelToEntity(user);
        return entityToModel(userRepository.save(e));
    }

    @Override
    public void deleteUser(Long id) {
        Optional<Users> e = userRepository.findById(id);
        e.ifPresent(x->{
            x.setStatus(DataStatusEnum.DELETED.name());
            x.setUpdatedAt(new Date());
            userRepository.save(x);
        });
    }

    private UserModel entityToModel(Users user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setPassword(user.getPassword());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setEmail(user.getEmail());
        userModel.setPhone(user.getPhone());
        userModel.setAddress(user.getAddress());
        return userModel;
    }

    private Users modelToEntity(UserModel userModel) {
        Users user = new Users();
        if (userModel.getId() != null) {
            user.setId(userModel.getId());
            user.setUpdatedAt(new Date());
            user.setStatus(DataStatusEnum.UPDATED.name());
        } else {
            user.setCreatedAt(new Date());
            user.setStatus(DataStatusEnum.CREATED.name());
        }
        user.setUsername(userModel.getUsername());
        user.setPassword(userModel.getPassword());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setPhone(userModel.getPhone());
        user.setAddress(userModel.getAddress());

        return user;
    }
}
