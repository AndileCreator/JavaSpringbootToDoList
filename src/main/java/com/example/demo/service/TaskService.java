package com.example.demo.service;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.Task;
import com.example.demo.enums.Status;
import com.example.demo.exception.DuplicateResourceException;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.repository.TaskRepository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Creating a new task
    @Transactional
    public TaskResponse createTask(TaskRequest taskRequest) {
        if (taskRepository.existsByTaskName(taskRequest.taskName())){
            throw new DuplicateResourceException(
                    "Task already exists with name "
            );
        }
        Task task = new Task();
        task.setTaskName(taskRequest.taskName());
        task.setDueDate(taskRequest.dueDate());
        task.setStatus(Status.valueOf(taskRequest.status()));

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    //Read ALL Tasks
    @Transactional(readOnly = true)
    public List<TaskResponse>getAllTasks(){
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    //Read One Task
    @Transactional(readOnly = true)
    public TaskResponse getTaskById(Long id){

        Task task = findTaskOrThrow(id);
        return mapToResponse(task);
    }
    private Task findTaskOrThrow(Long id){
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        "Task with ID:" + id + " does not exist."
                ));
    }
    //UPDATE STATUS
    @Transactional
    public TaskResponse updateTask(Long id,TaskRequest taskRequest) {
        Task task = findTaskOrThrow(id);
        task.setStatus(Status.valueOf(taskRequest.status()));

        Task updatedStatus = taskRepository.save(task);
        return mapToResponse(updatedStatus);

    }

    //DELETE
    @Transactional
    public void deleteTask(Long id){
        Task task = findTaskOrThrow(id);
        taskRepository.delete(task);
    }

    private TaskResponse mapToResponse(Task task){
        return new  TaskResponse(
                task.getId(),
                task.getTaskName(),
                task.getStatus(),
                task.getDueDate()
        );
    }

}
