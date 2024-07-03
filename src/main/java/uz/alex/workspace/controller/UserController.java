package uz.alex.workspace.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.alex.workspace.entity.Users;
import uz.alex.workspace.model.UserModel;
import uz.alex.workspace.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users currentUser = (Users) authentication.getPrincipal();
        log.info("Current user {}", currentUser);
        return ResponseEntity.ok(userService.getAllUsers());

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/update")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }
}

