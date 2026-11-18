package com.example.demo.controller;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.enums.Status;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class TaskController {

    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    //Create
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.createTask(taskRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskResponse);
    }
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {

        return ResponseEntity.ok(
                taskService.getAllTasks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                taskService.getTaskById(id));
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest taskRequest){
        return ResponseEntity.ok(
                taskService.updateTask(id, taskRequest)
        );
    }
    @DeleteMapping("/status")
    public ResponseEntity<TaskResponse> deleteTask(
            @PathVariable Long id){

        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
