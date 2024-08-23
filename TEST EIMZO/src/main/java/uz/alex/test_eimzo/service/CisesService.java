package uz.alex.test_eimzo.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.alex.test_eimzo.dto.ProductInfoResponse;

import java.util.List;

public interface CisesService {
     List<ProductInfoResponse> cisesInfoByCodeList(List<String> codes, String id, HttpServletRequest req);
}
