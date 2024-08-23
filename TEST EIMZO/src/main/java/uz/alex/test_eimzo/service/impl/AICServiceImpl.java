package uz.alex.test_eimzo.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.alex.test_eimzo.dto.AICDto;
import uz.alex.test_eimzo.dto.InfoComparedCisInfoDto;
import uz.alex.test_eimzo.dto.ProductInfoResponse;
import uz.alex.test_eimzo.model.AICModel;
import uz.alex.test_eimzo.repository.AICRepository;
import uz.alex.test_eimzo.service.AICService;
import uz.alex.test_eimzo.service.CisesService;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class AICServiceImpl implements AICService {
    private final CisesService cisesService;
    private final AICRepository aicRepository;
    private ModelMapper modelMapper;

    public AICServiceImpl(CisesService cisesService, AICRepository aicRepository) {
        this.cisesService = cisesService;
        this.aicRepository = aicRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public List<InfoComparedCisInfoDto> compareAIKDataWithRootApi(MultipartFile file, HttpServletRequest req) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        List<Map<String, String>> filteredData = new ArrayList<>();
        Set<String> codes = new HashSet<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0); // get the first sheet

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                row.forEach(cell -> rowData.add(cell.toString()));
                Map<String, String> dataRow = new HashMap<>();
                dataRow.put("code", rowData.get(0).replaceAll("[^0-9]", ""));
                dataRow.put("child", rowData.get(1));
                data.add(dataRow);
                codes.add(rowData.get(0).replaceAll("[^0-9]", ""));
            }
        }
        List<ProductInfoResponse> productInfoResponses = cisesService.cisesInfoByCodeList(codes.stream().toList(), null, req);
        List<InfoComparedCisInfoDto> resultList = new ArrayList<>();
        Set<String> codesSet = new HashSet<>();

        for (Map<String, String> dataRow : data) {
            String code = dataRow.get("code");
            if (codesSet.add(code)) {
                filteredData.add(dataRow);
            }
        }
        filteredData.forEach(x -> {
            Optional<ProductInfoResponse> optional = productInfoResponses.stream().peek(productInfoResponse -> {
                AICDto aicDto = modelMapper.map(productInfoResponse.getCisInfo(), AICDto.class);
                aicDto.setCode(productInfoResponse.getCisInfo().getCis());
                aicDto = saveAIC(aicDto);
                productInfoResponse.getCisInfo().setId(aicDto.getUuid());
            }).filter(item -> item.getCisInfo().getCis().equalsIgnoreCase(x.get("code"))).findFirst();
            if (optional.isPresent()) {
                int numberOfChileAslBelgi = optional.get().getCisInfo().getChild() != null ? optional.get().getCisInfo().getChild().size() : 0;
                int numberOfChildInputData = Double.valueOf(x.get("child")).intValue();
                InfoComparedCisInfoDto infoComparedCisInfoDto = new InfoComparedCisInfoDto();
                infoComparedCisInfoDto.setCis(x.get("code"));
                infoComparedCisInfoDto.setChildFromAslBelgi(optional.get().getCisInfo().getChild());
                infoComparedCisInfoDto.setNumberOfCMFromAslBelgi(numberOfChileAslBelgi);
                //TODO bu qismi exceldagi datalar aniq bolgandan keyin togirlanshi kerak
                infoComparedCisInfoDto.setNumberOfCMFromUploadedData(numberOfChildInputData);
                infoComparedCisInfoDto.setChildFromUploadedData(new ArrayList<>());
                infoComparedCisInfoDto.setAicDtoId(optional.get().getCisInfo().getId());
                infoComparedCisInfoDto.setStatus(numberOfChildInputData == numberOfChileAslBelgi);
                resultList.add(infoComparedCisInfoDto);
            }
        });
        return resultList;
    }

    @Override
    public AICDto saveAIC(AICDto aic) {
        AICModel e = modelMapper.map(aic, AICModel.class);
        try {
            e = aicRepository.save(e);
        } catch (Exception ex) {
            e = aicRepository.findByCode(e.getCode()).orElse(null);
        }
        return modelMapper.map(e, AICDto.class);
    }

    @Override
    public AICDto getAICById(String uuid) {
        Optional<AICModel> optional = aicRepository.findById(uuid);
        return optional.map(aicModel -> modelMapper.map(aicModel, AICDto.class)).orElse(null);
    }

    @Override
    public List<AICDto> getAllAICs() {
        return aicRepository.findAll().stream().map(i -> modelMapper.map(i, AICDto.class)).toList();
    }
}
