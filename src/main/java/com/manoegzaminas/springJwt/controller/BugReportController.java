package com.manoegzaminas.springJwt.controller;

import com.manoegzaminas.springJwt.model.BugReport;
import com.manoegzaminas.springJwt.model.Project;
import com.manoegzaminas.springJwt.service.BugReportService;
import com.manoegzaminas.springJwt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("/projects/{projectId}/bugReports")

public class BugReportController {


    @Autowired
    private BugReportService bugReportService;
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<BugReport>> findAllBugReports(@PathVariable Long projectId) {

        return ResponseEntity.ok(bugReportService.findByProject(projectService.findProjectById(projectId)));
    }

    @PostMapping("/addBugReport")
    public ResponseEntity<BugReport> createBugReport(@PathVariable Long projectId,
                                                     @RequestBody BugReport bugReport) {
        Optional<Project> project = projectService.findProjectById(projectId);
        if (project.isPresent()) {
            bugReport.setProject(project.get());
            bugReport = bugReportService.saveBugReport(bugReport);
            return ResponseEntity.status(HttpStatus.CREATED).body(bugReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteBugReport/{bugReportId}")
    public ResponseEntity<Void> deleteBugReport(@PathVariable Long projectId, @PathVariable Long bugReportId) {
        Optional<Project> project = projectService.findProjectById(projectId);
        if (project.isPresent()) {
            bugReportService.deleteBugReport(bugReportId);
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
    @PutMapping("/updateBugReport/{bugReportId}")
    public ResponseEntity<Optional<BugReport>> updateBugReport(@PathVariable Long projectId,
                                                               @PathVariable Long bugReportId,
                                                               @RequestBody BugReport updatedBugReport) {
        Optional<Project> project = projectService.findProjectById(projectId);
        if (!project.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Optional<BugReport> existingBugReport = bugReportService.findBugReportById(bugReportId);
        existingBugReport.get().setDescription(updatedBugReport.getDescription());
        existingBugReport.get().setScreenshotPath(updatedBugReport.getScreenshotPath());
        existingBugReport.get().setStatus(updatedBugReport.getStatus());

        bugReportService.saveBugReport(existingBugReport.orElse(null));


        return ResponseEntity.ok(existingBugReport);
    }




}
