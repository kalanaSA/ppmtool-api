package com.kalanasolutions.PPMToolapi.repository;

import com.kalanasolutions.PPMToolapi.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    Optional<Project> findByName(String name);

    Optional<Project> findByProjectIdentifier(String ProjectIdentifier);

    @Transactional
    Long deleteByProjectIdentifier(String id);

}
