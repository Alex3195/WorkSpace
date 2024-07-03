package uz.alex.workspace.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.alex.workspace.entity.Users;
import uz.alex.workspace.model.LoginResponse;
import uz.alex.workspace.model.LoginUserModel;
import uz.alex.workspace.model.UserModel;
import uz.alex.workspace.repositories.UserRepository;
import uz.alex.workspace.service.JwtService;
import uz.alex.workspace.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<Users> users = userRepository.findByIsEnabled(true);
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
        e.ifPresent(x -> {
            x.setIsEnabled(false);
            x.setUpdatedAt(new Date());
            userRepository.save(x);
        });
    }

    @Override
    public LoginResponse authenticate(LoginUserModel input) {
        Users user = userRepository.findByUsername(input.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return loginResponse;
    }

    private UserModel entityToModel(Users user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setUsername(user.getUsername());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setPhone(user.getPhone());
        userModel.setRole(user.getRole());
        return userModel;
    }

    private Users modelToEntity(UserModel userModel) {
        Users user = new Users();
        if (userModel.getId() != null) {
            user.setId(userModel.getId());
        }
        user.setUsername(userModel.getUsername());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPhone(userModel.getPhone());
        user.setRole(userModel.getRole());

        return user;
    }
}
