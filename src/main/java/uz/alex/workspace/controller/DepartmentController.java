package uz.alex.workspace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alex.workspace.form.DataTableForm;
import uz.alex.workspace.form.FilterForm;
import uz.alex.workspace.model.DepartmentModel;
import uz.alex.workspace.service.DepartmentService;


@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentModel> getDepartment(@PathVariable int id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<DataTableForm> getAllDepartments(@RequestBody FilterForm filter) {
        return ResponseEntity.ok(departmentService.getAllDepartments(filter));
    }

    @PostMapping("/add")
    public ResponseEntity<DepartmentModel> addDepartment(@RequestBody DepartmentModel department) {
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }

    @PutMapping("/update")
    public ResponseEntity<DepartmentModel> updateDepartment(@RequestBody DepartmentModel department) {
        return ResponseEntity.ok(departmentService.updateDepartment(department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }
}
