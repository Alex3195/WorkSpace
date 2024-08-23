package uz.alex.test_eimzo.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.alex.test_eimzo.dto.AuthRequestDto;
import uz.alex.test_eimzo.service.AuthService;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    private String URL_AUTH_KEY = "https://aslbelgisi.uz/api/v3/true-api/auth/key";
    private String URL_SIMPLE_SIGN_IN = "https://aslbelgisi.uz/api/v3/true-api/auth/simpleSignIn";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<?> getAuthKey() {
        Map<String, String> res = restTemplate.getForObject(URL_AUTH_KEY, Map.class);
        return ResponseEntity.ok(res);
    }

    @Override
    public ResponseEntity<?> getToken(AuthRequestDto authRequestDto) {
        LinkedHashMap<String, String> response = restTemplate.postForObject(URL_SIMPLE_SIGN_IN, authRequestDto, LinkedHashMap.class);
        return ResponseEntity.ok(response);
    }
}
