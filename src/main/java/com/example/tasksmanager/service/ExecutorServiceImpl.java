package com.example.tasksmanager.service;

import com.example.tasksmanager.entity.Executor;
import com.example.tasksmanager.exception.ExecutorNotFoundException;
import com.example.tasksmanager.repository.ExecutorRepository;
import com.example.tasksmanager.service.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final ExecutorRepository executorRepository;

    @Autowired
    public ExecutorServiceImpl(ExecutorRepository executorRepository) {
        this.executorRepository = executorRepository;
    }

    @Override
    public Executor addExecutor(Executor executor) {
        return executorRepository.save(executor);
    }

    @Override
    public List<Executor> getAllExecutors() {
        List<Executor> executors = executorRepository.findAll();
        executors.sort(Comparator.comparingLong(Executor::getId));
        return executors;
    }

    @Override
    public Executor getExecutorById(Long executorId) {
        return executorRepository.findById(executorId)
                .orElseThrow(() -> new ExecutorNotFoundException(executorId));
    }

    @Override
    public Executor updateExecutor(Long executorId, Executor updatedExecutor) {
        Executor executor = getExecutorById(executorId);
        executor.setFirstName(updatedExecutor.getFirstName());
        executor.setLastName(updatedExecutor.getLastName());
        return executorRepository.save(executor);
    }

    @Override
    public void deleteExecutor(Long executorId) {
        executorRepository.deleteById(executorId);
    }
}