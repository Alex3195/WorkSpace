package uz.alex.workspace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alex.workspace.form.DataTableForm;
import uz.alex.workspace.form.FilterForm;
import uz.alex.workspace.model.GroupOfRolesModel;
import uz.alex.workspace.service.GroupOfRolesService;


@RestController
@RequestMapping("/api/group-of-roles")
public class GroupOfRolesController {
    private final GroupOfRolesService service;

    public GroupOfRolesController(GroupOfRolesService service) {
        this.service = service;
    }

    @PostMapping("/all")
    private ResponseEntity<DataTableForm> getAllGroupOfRoles(@RequestBody FilterForm form) {
        return ResponseEntity.ok(service.getAllGroupOfRoles(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupOfRolesModel> getByIdGroupOfRoles(@PathVariable int id) {
        return ResponseEntity.ok(service.getGroupOfRolesById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<GroupOfRolesModel> saveGroupOfRoles(@RequestBody GroupOfRolesModel data) {
        return ResponseEntity.ok(service.saveGroupOfRoles(data));
    }

    @PutMapping("/update")
    public ResponseEntity<GroupOfRolesModel> updateGroupOfRoles(@RequestBody GroupOfRolesModel data) {
        return ResponseEntity.ok(service.updateGroupOfRoles(data));
    }

    @DeleteMapping("/{id}")
    public void deleteGroupOfRoles(@PathVariable int id) {
        service.deleteGroupOfRoles(id);
    }

}
