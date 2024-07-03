package uz.alex.workspace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alex.workspace.model.EmployeeModel;
import uz.alex.workspace.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> getEmployee(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeModel>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeModel> addEmployee(@RequestBody EmployeeModel employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeModel> updateEmployee(@RequestBody EmployeeModel employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(employee));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
