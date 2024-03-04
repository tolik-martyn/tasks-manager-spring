package com.example.tasksmanager.service;

import com.example.tasksmanager.model.Executor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExecutorService {

    Executor addExecutor(Executor executor);

    List<Executor> getAllExecutors();

    Executor getExecutorById(Long executorId);

    Executor updateExecutor(Long executorId, Executor updatedExecutor);

    void deleteExecutor(Long executorId);
}