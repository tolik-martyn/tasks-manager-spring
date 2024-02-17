package com.example.tasksmanager.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId) {
        super("Task not found with id: " + taskId);
    }
}