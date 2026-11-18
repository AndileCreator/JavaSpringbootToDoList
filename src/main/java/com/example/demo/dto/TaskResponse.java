package com.example.demo.dto;

public record TaskResponse(
        Long id,
        String taskName,
        com.example.demo.enums.Status status,
        String dueDate
) {
}
