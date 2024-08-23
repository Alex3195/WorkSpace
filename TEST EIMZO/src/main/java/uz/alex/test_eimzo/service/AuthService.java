package uz.alex.test_eimzo.service;

import org.springframework.http.ResponseEntity;
import uz.alex.test_eimzo.dto.AuthRequestDto;

public interface AuthService {
    ResponseEntity<?> getAuthKey();

    ResponseEntity<?> getToken(AuthRequestDto auth);
}
