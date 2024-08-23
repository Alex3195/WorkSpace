package uz.alex.test_eimzo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.alex.test_eimzo.dto.MarkingCodeDto;
import uz.alex.test_eimzo.model.MarkingCodeModel;
import uz.alex.test_eimzo.repository.MarkingCodeRepository;
import uz.alex.test_eimzo.service.MarkingCodeService;

import java.util.List;
import java.util.Optional;

@Service
public class MarkingCodeServiceImpl implements MarkingCodeService {
    private final MarkingCodeRepository markingCodeRepository;
    private ModelMapper modelMapper;

    public MarkingCodeServiceImpl(MarkingCodeRepository markingCodeRepository) {
        this.markingCodeRepository = markingCodeRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public MarkingCodeDto addMarkingCode(MarkingCodeDto markingCodeDto) {
        MarkingCodeModel e = modelMapper.map(markingCodeDto, MarkingCodeModel.class);
        e = markingCodeRepository.save(e);
        return modelMapper.map(e, MarkingCodeDto.class);
    }

    @Override
    public void deleteMarkingCode(String uuid) {
        markingCodeRepository.deleteById(uuid);
    }

    @Override
    public MarkingCodeDto getMarkingCode(String uuid) {
        Optional<MarkingCodeModel> optional = markingCodeRepository.findById(uuid);
        return optional.map(markingCodeModel -> modelMapper.map(markingCodeModel, MarkingCodeDto.class)).orElse(null);
    }

    @Override
    public List<MarkingCodeDto> getAllMarkingCodes() {
        return markingCodeRepository.findAll().stream().map(i -> modelMapper.map(i, MarkingCodeDto.class)).toList();
    }
}
