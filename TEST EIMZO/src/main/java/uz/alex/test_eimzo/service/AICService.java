package uz.alex.test_eimzo.service;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.set.ListOrderedSet;
import org.springframework.web.multipart.MultipartFile;
import uz.alex.test_eimzo.dto.AICDto;
import uz.alex.test_eimzo.dto.InfoComparedCisInfoDto;

import java.io.IOException;
import java.util.List;

public interface AICService {
    List<InfoComparedCisInfoDto> compareAIKDataWithRootApi(MultipartFile file, HttpServletRequest req) throws IOException;

    AICDto saveAIC(AICDto aic);

    AICDto getAICById(String uuid);

    List<AICDto> getAllAICs();
}
