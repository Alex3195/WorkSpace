package uz.alex.test_eimzo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alex.test_eimzo.dto.ProductInfoResponse;
import uz.alex.test_eimzo.service.CisesService;

import java.util.List;

@RestController
@RequestMapping("/api/cises")
public class CisesController {
    private final CisesService cisesService;

    public CisesController(CisesService cisesService) {
        this.cisesService = cisesService;
    }

    @CrossOrigin("*")
    @PostMapping("/info/{id}")
    public ResponseEntity<List<ProductInfoResponse>> info(@PathVariable("id") String id, @RequestBody List<String> codes, HttpServletRequest request) {
        return ResponseEntity.ok(cisesService.cisesInfoByCodeList(codes,id, request));
    }
}
