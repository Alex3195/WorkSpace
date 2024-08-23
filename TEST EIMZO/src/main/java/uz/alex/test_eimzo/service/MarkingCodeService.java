package uz.alex.test_eimzo.service;

import uz.alex.test_eimzo.dto.MarkingCodeDto;

import java.util.List;

public interface MarkingCodeService {
    MarkingCodeDto addMarkingCode(MarkingCodeDto markingCodeDto);

    void deleteMarkingCode(String uuid);

    MarkingCodeDto getMarkingCode(String uuid);

    List<MarkingCodeDto> getAllMarkingCodes();
}
