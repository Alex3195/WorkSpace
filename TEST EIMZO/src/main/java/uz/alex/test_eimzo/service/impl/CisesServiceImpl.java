package uz.alex.test_eimzo.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uz.alex.test_eimzo.dto.MarkingCodeDto;
import uz.alex.test_eimzo.dto.ProductDto;
import uz.alex.test_eimzo.dto.ProductInfoResponse;
import uz.alex.test_eimzo.model.AICModel;
import uz.alex.test_eimzo.repository.AICRepository;
import uz.alex.test_eimzo.service.CisesService;
import uz.alex.test_eimzo.service.MarkingCodeService;
import uz.alex.test_eimzo.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class CisesServiceImpl implements CisesService {
    private String URL_CISES_INFO = "https://aslbelgisi.uz/api/v3/true-api/cises/info";
    private final MarkingCodeService markingCodeService;
    private ModelMapper modelMapper;
    private final ProductService productService;
    private final AICRepository aicRepository;

    public CisesServiceImpl(MarkingCodeService markingCodeService, ProductService productService, AICRepository aicRepository) {
        this.markingCodeService = markingCodeService;
        this.productService = productService;
        this.aicRepository = aicRepository;
        modelMapper = new ModelMapper();
    }

    @Override
    public List<ProductInfoResponse> cisesInfoByCodeList(List<String> codes, String id, HttpServletRequest req) {
        List<ProductInfoResponse> responses = new ArrayList<>();

        if (codes.size() > 1000) {
            List<List<String>> splitedList = splitListIntoChunks(codes, 1000);
            for (List<String> list : splitedList) {
                responses.addAll(getData(list, req));
            }
        } else {
            responses = getData(codes, req);
        }
        if (id != null && !id.isBlank()) {
            saveChildToDB(responses, id);
        }
        return responses;
    }

    private void saveChildToDB(List<ProductInfoResponse> list, String id) {
        list.forEach(item -> {
            MarkingCodeDto dto = modelMapper.map(item.getCisInfo(), MarkingCodeDto.class);
            ProductDto productDto = modelMapper.map(item.getCisInfo(), ProductDto.class);
            Optional<AICModel> aicModel = aicRepository.findByCode(id);
            try {
                var prod = productService.addProduct(productDto);
                dto.setPackageType(id);
                aicModel.ifPresent(itemAic -> dto.setAicUUID(itemAic.getUuid()));
                dto.setProductUUID(prod.getUuid());
                dto.setCode(item.getCisInfo().getCis());
                markingCodeService.addMarkingCode(dto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private List<List<String>> splitListIntoChunks(List<String> list, int chunkSize) {
        int totalSize = list.size();
        int numberOfChunks = (int) Math.ceil((double) totalSize / chunkSize);

        List<List<String>> chunks = new ArrayList<>(numberOfChunks);

        IntStream.range(0, numberOfChunks).forEach(i -> {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, totalSize);
            chunks.add(new ArrayList<>(list.subList(start, end)));
        });

        return chunks;
    }

    private List<ProductInfoResponse> getData(List<String> codes, HttpServletRequest req) {
        String auth = req.getHeader("Authorization");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", auth);
        HttpEntity<List<String>> entity = new HttpEntity<>(codes, headers);
        ResponseEntity<List<ProductInfoResponse>> response = null;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_CISES_INFO);
        try {
            response = restTemplate.exchange(builder.toUriString(),
                    HttpMethod.POST,
                    entity, new ParameterizedTypeReference<List<ProductInfoResponse>>() {
                    });
            return Objects.requireNonNull(response).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
