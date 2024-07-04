package uz.alex.workspace.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alex.workspace.model.ProjectModel;
import uz.alex.workspace.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/project")
//@CrossOrigin(allowedHeaders = "*",origins = "http://localhost3000")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectModel> getProject(@PathVariable int id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectModel>> getAllProjects() {
        return ResponseEntity.ok(projectService.getProjects());
    }

    @PostMapping("/add")
    public ResponseEntity<ProjectModel> addProject(@RequestBody ProjectModel project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @PutMapping("/update")
    public ResponseEntity<ProjectModel> updateProject(@RequestBody ProjectModel project) {
        return ResponseEntity.ok(projectService.updateProject(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return ResponseEntity.accepted().build();
    }
}

