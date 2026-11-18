package com.example.demo.repository;

import com.example.demo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean existsByTask(String taskName);
    boolean existsByDate(String dueDate);
    Optional<Task> findByStatus(String status);
}
