package com.example.demo.repository;

import com.example.demo.entity.Task;
import com.example.demo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByTaskName(String taskName);
    Optional<Task> findByTaskName(String taskName);
}
