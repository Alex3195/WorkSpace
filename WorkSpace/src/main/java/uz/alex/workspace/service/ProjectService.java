package uz.alex.workspace.service;

import uz.alex.workspace.model.ProjectModel;

import java.util.List;

public interface ProjectService {
    List<ProjectModel> getProjects();

    ProjectModel getProject(int id);

    ProjectModel createProject(ProjectModel project);

    ProjectModel updateProject(ProjectModel project);

    void deleteProject(int id);
}
