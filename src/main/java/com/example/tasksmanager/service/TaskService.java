package com.example.tasksmanager.service;

import com.example.tasksmanager.model.Task;
import com.example.tasksmanager.model.TaskStatus;

import java.util.List;

public interface TaskService {
    Task addTask(Task task);

    List<Task> getAllTasks();

    List<Task> getTasksByStatus(TaskStatus status);

    Task updateTaskStatus(Long taskId, TaskStatus status);

    void deleteTask(Long taskId);

    Task getTaskById(Long taskId);

    Task updateTask(Task task);
}