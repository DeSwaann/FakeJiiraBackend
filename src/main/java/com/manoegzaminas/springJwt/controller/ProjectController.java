package com.manoegzaminas.springJwt.controller;

import com.manoegzaminas.springJwt.model.Project;
import com.manoegzaminas.springJwt.service.ProjectService;
import com.manoegzaminas.springJwt.service.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;


    @GetMapping
    @PermitAll
    public ResponseEntity<List<Project>> findAllProjects() {
        return ResponseEntity.ok(projectService.findAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Project>> findProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.findProjectById(id));
    }

    @PostMapping("/addProject")
    public ResponseEntity<Project> addNewProject(@RequestBody Project project) {

        Project existingProject = projectService.findProjectByName(project.getName());

        if(existingProject == null) {
            Project savedProject = projectService.saveProject(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/deleteProject/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId) {
        Optional<Project> deletedProject = projectService.findProjectById(projectId);
        if (deletedProject.isPresent()) {
            projectService.deleteProject(projectId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateProject/{projectId}")
    public ResponseEntity<Optional<Project>> updateProject(@PathVariable Long projectId,
                                                           @RequestBody Project updatedProject) {
        Optional<Project> existingProject = projectService.findProjectById(projectId);
        if (existingProject.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        existingProject.get().setName(updatedProject.getName());
        existingProject.get().setDescription(updatedProject.getDescription());

        projectService.saveProject(existingProject.orElse(null));

        return ResponseEntity.ok(existingProject);
    }

}
