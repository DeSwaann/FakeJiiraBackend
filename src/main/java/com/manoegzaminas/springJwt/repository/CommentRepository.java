package com.manoegzaminas.springJwt.repository;

import com.manoegzaminas.springJwt.model.BugReport;
import com.manoegzaminas.springJwt.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByBugReport(BugReport bugReport);
    List<CommentEntity> findByBugReportId(Long aLong);



}
