package uz.alex.workspace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alex.workspace.model.RoleModel;
import uz.alex.workspace.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RoleModel>> getAllRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleModel> getRoleById(@PathVariable("id") int id) {
        return ResponseEntity.ok(roleService.getRole(id));
    }

    @PostMapping("/add")
    public ResponseEntity<RoleModel> addRole(@RequestBody RoleModel role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @PutMapping("/update")
    public ResponseEntity<RoleModel> updateRole(@RequestBody RoleModel role) {
        return ResponseEntity.ok(roleService.updateRole(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoleModel> deleteRole(@PathVariable("id") int id) {
        roleService.deleteRole(id);
        return ResponseEntity.accepted().build();
    }
}
