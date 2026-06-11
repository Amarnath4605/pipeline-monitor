package com.devops.pipelinemonitor.repository;

import com.devops.pipelinemonitor.entity.Pipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PipelineRepository extends JpaRepository<Pipeline, Long> {
    List<Pipeline> findByStatus(String status);
    List<Pipeline> findByTriggeredBy(String triggeredBy);
    List<Pipeline> findByProjectName(String projectName);
}
