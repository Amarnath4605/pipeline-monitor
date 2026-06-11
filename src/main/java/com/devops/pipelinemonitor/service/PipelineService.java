package com.devops.pipelinemonitor.service;

import com.devops.pipelinemonitor.dto.PipelineRequest;
import com.devops.pipelinemonitor.entity.Pipeline;
import com.devops.pipelinemonitor.repository.PipelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PipelineService {

    @Autowired
    private PipelineRepository pipelineRepository;

    // Create Pipeline
    public Pipeline createPipeline(PipelineRequest request) {
        Pipeline pipeline = new Pipeline();
        pipeline.setName(request.getName());
        pipeline.setProjectName(request.getProjectName());
        pipeline.setStatus(request.getStatus() != null ? request.getStatus() : "PENDING");
        pipeline.setTriggeredBy(request.getTriggeredBy());
        return pipelineRepository.save(pipeline);
    }

    // Get All Pipelines
    public List<Pipeline> getAllPipelines() {
        return pipelineRepository.findAll();
    }

    // Get Pipeline by ID
    public Pipeline getPipelineById(Long id) {
        Optional<Pipeline> pipeline = pipelineRepository.findById(id);
        return pipeline.orElse(null);
    }

    // Update Pipeline Status
    public Pipeline updateStatus(Long id, String status) {
        Optional<Pipeline> pipelineOpt = pipelineRepository.findById(id);
        if (pipelineOpt.isEmpty()) return null;

        Pipeline pipeline = pipelineOpt.get();
        pipeline.setStatus(status);
        return pipelineRepository.save(pipeline);
    }

    // Delete Pipeline
    public String deletePipeline(Long id) {
        if (!pipelineRepository.existsById(id)) {
            return "Pipeline not found!";
        }
        pipelineRepository.deleteById(id);
        return "Pipeline deleted successfully!";
    }

    // Get by Status
    public List<Pipeline> getByStatus(String status) {
        return pipelineRepository.findByStatus(status);
    }
}
