package com.manoegzaminas.springJwt.service;

import com.manoegzaminas.springJwt.model.Project;
import com.manoegzaminas.springJwt.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }
    public Optional<Project> findProjectById(Long id) {
        if(projectRepository.findById(id).isPresent()) {
            return projectRepository.findById(id);
        }
        return Optional.empty();
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }


    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }


    public Project findProjectByName(String name) {
        return projectRepository.findProjectByName(name);
    }
}
