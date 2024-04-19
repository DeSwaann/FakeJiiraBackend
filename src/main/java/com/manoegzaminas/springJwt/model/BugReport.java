package com.manoegzaminas.springJwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String description;
    private String screenshotPath;
    private String status;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @OneToMany(mappedBy = "bugReport")
    @JsonIgnore
    private List<CommentEntity> commentEntity;
}
