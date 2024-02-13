package com.example.tasksmanager;

public class ExecutorNotFoundException extends RuntimeException {
    public ExecutorNotFoundException(Long executorId) {
        super("Executor not found with id: " + executorId);
    }
}
