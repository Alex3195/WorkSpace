package uz.alex.test_eimzo.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    public List<List<String>> readExcelFile(MultipartFile file) throws IOException {
        List<List<String>> data = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // get the first sheet

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                row.forEach(cell -> rowData.add(cell.toString()));
                data.add(rowData);
            }
        }
        return data;
    }
}
