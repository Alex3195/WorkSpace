package uz.alex.workspace.service.impl;

import org.springframework.stereotype.Service;
import uz.alex.workspace.model.ProjectModel;
import uz.alex.workspace.repositories.ProjectRepository;
import uz.alex.workspace.service.ProjectService;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectModel> getProjects() {
        return List.of();
    }

    @Override
    public ProjectModel getProject(int id) {
        return null;
    }

    @Override
    public ProjectModel createProject(ProjectModel project) {
        return null;
    }

    @Override
    public ProjectModel updateProject(ProjectModel project) {
        return null;
    }

    @Override
    public void deleteProject(int id) {

    }
}
