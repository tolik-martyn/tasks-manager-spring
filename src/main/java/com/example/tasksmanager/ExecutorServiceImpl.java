package com.example.tasksmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return executorRepository.findAll();
    }

    @Override
    public Executor getExecutorById(Long executorId) {
        return executorRepository.findById(executorId)
                .orElseThrow(() -> new RuntimeException("Executor not found"));
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