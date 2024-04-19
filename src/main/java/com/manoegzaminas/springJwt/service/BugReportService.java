package com.manoegzaminas.springJwt.service;

import com.manoegzaminas.springJwt.model.BugReport;
import com.manoegzaminas.springJwt.model.Project;
import com.manoegzaminas.springJwt.repository.BugReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BugReportService {
    @Autowired
    private final BugReportRepository bugReportRepository;

    public BugReportService(BugReportRepository bugReportRepository) {
        this.bugReportRepository = bugReportRepository;
    }


    public List<BugReport> findAllBugReports() {
        return bugReportRepository.findAll();
    }

    public Optional<BugReport> findBugReportById(Long id) {
        if (bugReportRepository.findById(id).isPresent()) {
            return bugReportRepository.findById(id);
        }
        return Optional.empty();
    }

    public List<BugReport> findByProject(Optional<Project> project) {
        return bugReportRepository.findByProject(project.get());
    }
    public Optional<BugReport> getBugReport(Long projectId, Long bugReportId) {

        Optional<BugReport> bugReport = findBugReportById(bugReportId);
        if (bugReport.isPresent() && bugReport.get().getProject().getId().equals(projectId)) {
            return bugReport;
        }
        return Optional.empty();
    }

    public BugReport saveBugReport(BugReport bugReport) {
        return bugReportRepository.save(bugReport);
    }

    public void deleteBugReport(Long bugReportId) {
        bugReportRepository.deleteById(bugReportId);
    }
}
