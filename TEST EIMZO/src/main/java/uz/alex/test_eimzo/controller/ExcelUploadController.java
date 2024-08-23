package uz.alex.test_eimzo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.alex.test_eimzo.dto.InfoComparedCisInfoDto;
import uz.alex.test_eimzo.service.AICService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExcelUploadController {

    private final AICService AICService;

    @Autowired
    public ExcelUploadController( AICService AICService) {
        this.AICService = AICService;
    }

    @CrossOrigin("*")
    @PostMapping("/upload")
    public List<InfoComparedCisInfoDto> uploadExcelFile(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
        try {
            return AICService.compareAIKDataWithRootApi(file, req);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to process file");
        }
    }
}
