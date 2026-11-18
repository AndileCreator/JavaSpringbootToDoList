package com.example.demo.dto;

public record TaskResponse(
        Long id,
        String taskName,
        String status,
        String dueDate
) {
}
