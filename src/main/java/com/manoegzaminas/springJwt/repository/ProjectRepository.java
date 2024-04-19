package com.manoegzaminas.springJwt.repository;

import com.manoegzaminas.springJwt.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findProjectByName(String name);
}
