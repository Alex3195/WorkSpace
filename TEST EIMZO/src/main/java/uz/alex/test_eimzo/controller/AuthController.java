package uz.alex.test_eimzo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alex.test_eimzo.dto.AuthRequestDto;
import uz.alex.test_eimzo.service.AuthService;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/get/auth/key")
    @CrossOrigin("*")
    public ResponseEntity<?> data() {
        return authService.getAuthKey();
    }

    @CrossOrigin("*")
    @PostMapping("/simpleSignIn")
    public ResponseEntity<?> simpleSignIn(@RequestBody AuthRequestDto authRequestDto) {
        return authService.getToken(authRequestDto);
    }
}
