package com.manoegzaminas.springJwt.repository;

import com.manoegzaminas.springJwt.model.BugReport;
import com.manoegzaminas.springJwt.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugReportRepository extends JpaRepository<BugReport, Long> {
    List<BugReport> findByProject(Project project);
}
