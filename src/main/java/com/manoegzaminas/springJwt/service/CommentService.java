package com.manoegzaminas.springJwt.service;

import com.manoegzaminas.springJwt.model.BugReport;
import com.manoegzaminas.springJwt.model.CommentEntity;
import com.manoegzaminas.springJwt.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;


    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentEntity> findAllComments() {
        return commentRepository.findAll();
    }
    public List<CommentEntity> findByBugReport(Optional<BugReport> bugReport) {
        return commentRepository.findByBugReport(bugReport.get());
    }
    public List<CommentEntity> findByBugReportId(Optional<Long> bugReportId) {
        return commentRepository.findByBugReportId(bugReportId.get());
    }

    public List<CommentEntity> getComments(BugReport bugReport) {
        return commentRepository.findByBugReport(bugReport);
    }

    public CommentEntity saveComment(CommentEntity comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Optional<CommentEntity> findCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }
}