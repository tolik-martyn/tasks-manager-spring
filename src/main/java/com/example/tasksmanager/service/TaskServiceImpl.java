package com.example.tasksmanager.service;

import com.example.tasksmanager.aspect.TrackUserAction;
import com.example.tasksmanager.model.Task;
import com.example.tasksmanager.model.TaskStatus;
import com.example.tasksmanager.exception.TaskNotFoundException;
import com.example.tasksmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @TrackUserAction
    public Task addTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.NOT_STARTED);
        return taskRepository.save(task);
    }

    @Override
    @TrackUserAction
    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        tasks.sort(Comparator.comparingLong(Task::getId));
        return tasks;
    }

    @Override
    @TrackUserAction
    public List<Task> getTasksByStatus(TaskStatus status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        tasks.sort(Comparator.comparingLong(Task::getId));
        return tasks;
    }

    @Override
    @TrackUserAction
    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = getTaskById(taskId);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Override
    @TrackUserAction
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    @TrackUserAction
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @Override
    @TrackUserAction
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
}