package uz.alex.workspace.service.impl;

import org.springframework.stereotype.Service;
import uz.alex.workspace.constants.DataStatusEnum;
import uz.alex.workspace.entity.Project;
import uz.alex.workspace.model.ProjectModel;
import uz.alex.workspace.repositories.ProjectRepository;
import uz.alex.workspace.service.ProjectService;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectModel> getProjects() {
        return projectRepository.findAllActiveProjects().stream().map(this::entityToModel).toList();

    }

    @Override
    public ProjectModel getProject(int id) {
        return projectRepository.findById(id).map(this::entityToModel).orElse(null);
    }

    @Override
    public ProjectModel createProject(ProjectModel project) {
        return entityToModel(projectRepository.save(modelToEntity(project)));
    }

    @Override
    public ProjectModel updateProject(ProjectModel project) {
        return entityToModel(projectRepository.save(modelToEntity(project)));
    }

    @Override
    public void deleteProject(int id) {
        Optional<Project> project = projectRepository.findById(id);
        project.ifPresent(x -> {
            x.setDataStatus(DataStatusEnum.DELETED.name());
            projectRepository.save(x);
        });

    }

    private ProjectModel entityToModel(Project project) {
        ProjectModel projectModel = new ProjectModel();
        projectModel.setId(project.getId());
        projectModel.setName(project.getName());
        projectModel.setResponsibleLeaderId(project.getResponsibleLeaderId());
        projectModel.setBudgetSum(project.getBudgetSum());
        projectModel.setSpentSum(project.getSpentSum());
        projectModel.setResidualSum(project.getResidualSum());

        return projectModel;
    }

    private Project modelToEntity(ProjectModel projectModel) {
        Project project = new Project();
        project.setId(projectModel.getId());
        project.setName(projectModel.getName());
        project.setResponsibleLeaderId(projectModel.getResponsibleLeaderId());
        project.setBudgetSum(projectModel.getBudgetSum());
        project.setSpentSum(projectModel.getSpentSum());
        project.setResidualSum(projectModel.getResidualSum());
        if (projectModel.getId() != null) {
            project.setDataStatus(DataStatusEnum.UPDATED.name());
        } else {
            project.setDataStatus(DataStatusEnum.CREATED.name());
        }
        return project;
    }
}
