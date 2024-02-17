package com.example.tasksmanager.service;

import com.example.tasksmanager.model.Executor;

import java.util.List;

public interface ExecutorService {

    Executor addExecutor(Executor executor);

    List<Executor> getAllExecutors();

    Executor getExecutorById(Long executorId);

    Executor updateExecutor(Long executorId, Executor updatedExecutor);

    void deleteExecutor(Long executorId);
}