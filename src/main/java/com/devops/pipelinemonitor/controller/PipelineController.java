package com.devops.pipelinemonitor.controller;

import com.devops.pipelinemonitor.dto.PipelineRequest;
import com.devops.pipelinemonitor.entity.Pipeline;
import com.devops.pipelinemonitor.service.PipelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pipelines")
public class PipelineController {

    @Autowired
    private PipelineService pipelineService;

    // Create Pipeline
    @PostMapping
    public ResponseEntity<Pipeline> createPipeline(@RequestBody PipelineRequest request) {
        Pipeline pipeline = pipelineService.createPipeline(request);
        return ResponseEntity.ok(pipeline);
    }

    // Get All Pipelines
    @GetMapping
    public ResponseEntity<List<Pipeline>> getAllPipelines() {
        return ResponseEntity.ok(pipelineService.getAllPipelines());
    }

    // Get Pipeline by ID
    @GetMapping("/{id}")
    public ResponseEntity<Pipeline> getPipelineById(@PathVariable Long id) {
        Pipeline pipeline = pipelineService.getPipelineById(id);
        if (pipeline == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pipeline);
    }

    // Update Pipeline Status
    @PutMapping("/{id}/status")
    public ResponseEntity<Pipeline> updateStatus(@PathVariable Long id,
                                                  @RequestParam String status) {
        Pipeline pipeline = pipelineService.updateStatus(id, status);
        if (pipeline == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pipeline);
    }

    // Delete Pipeline
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePipeline(@PathVariable Long id) {
        return ResponseEntity.ok(pipelineService.deletePipeline(id));
    }

    // Get by Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Pipeline>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(pipelineService.getByStatus(status));
    }
}
