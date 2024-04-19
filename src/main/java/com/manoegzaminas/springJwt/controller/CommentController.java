package com.manoegzaminas.springJwt.controller;

import com.manoegzaminas.springJwt.model.BugReport;
import com.manoegzaminas.springJwt.model.CommentEntity;
import com.manoegzaminas.springJwt.service.BugReportService;
import com.manoegzaminas.springJwt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@Controller
@RequestMapping("/bugReports/{bugReportId}/comments")
public class CommentController {
    @Autowired
    private BugReportService bugReportService;
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentEntity>> getComments(@PathVariable("bugReportId") Long bugReportId) {
        Optional<BugReport> bugReport = bugReportService.findBugReportById(bugReportId);
        return bugReport.map(report -> ResponseEntity.ok(commentService.getComments(report)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/addComment")
    public ResponseEntity<CommentEntity> createComment(@PathVariable("bugReportId") Long bugReportId,
                                                       @RequestBody CommentEntity comment) {
        Optional<BugReport> bugReport = bugReportService.findBugReportById(bugReportId);
        if (bugReport.isPresent()) {
            comment.setBugReport(bugReport.get());
            comment = commentService.saveComment(comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("bugReportId") Long bugReportId,
                                              @PathVariable("commentId") Long commentId) {
        Optional<BugReport> bugReport = bugReportService.findBugReportById(bugReportId);
        if (bugReport.isPresent()) {
            commentService.deleteComment(commentId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/updateComment/{commentId}")
    public ResponseEntity<Optional<CommentEntity>> updateComment(@PathVariable("bugReportId") Long bugReportId,
                                                                 @PathVariable("commentId") Long commentId,
                                                                 @RequestBody CommentEntity updatedComment) {
        Optional<BugReport> bugReport = bugReportService.findBugReportById(bugReportId);
        if(!bugReport.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Optional<CommentEntity> existingComment = commentService.findCommentById(commentId);
        existingComment.get().setContent(updatedComment.getContent());
        commentService.saveComment(existingComment.orElse(null));
        return ResponseEntity.ok(existingComment);
    }


}
