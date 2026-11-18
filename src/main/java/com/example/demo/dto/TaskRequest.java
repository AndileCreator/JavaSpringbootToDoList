package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequest(
        @NotBlank(message = "Name of the task is Required")
        @Size(max =250, message = "Keep the name of the Task short and simple.")
        String taskName,

        @NotBlank(message = "Task status is required")
        @Size(max = 20, message = "Enter correct status")
        String status,

        @NotBlank(message = "Due date is required for each Task")
        @Size(max = 20, message = "Invalid due date")
        String dueDate
) {
}
