package uz.alex.workspace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.alex.workspace.model.LoginResponse;
import uz.alex.workspace.model.LoginUserModel;
import uz.alex.workspace.model.UserModel;
import uz.alex.workspace.service.UserService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {


    private final UserService authenticationService;

    public AuthenticationController(UserService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserModel> register(@RequestBody UserModel registerUserDto) {
        UserModel registeredUser = authenticationService.createUser(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserModel loginUserDto) {
        LoginResponse loginResponse = authenticationService.authenticate(loginUserDto);



        return ResponseEntity.ok(loginResponse);
    }
}