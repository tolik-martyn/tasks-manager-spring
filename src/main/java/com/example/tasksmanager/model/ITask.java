package com.example.tasksmanager.model;

import java.time.LocalDateTime;
import java.util.Set;

public interface ITask {
    Long getId();

    void setId(Long id);

    String getDescription();

    void setDescription(String description);

    TaskStatus getStatus();

    void setStatus(TaskStatus status);

    LocalDateTime getCreatedAt();

    void setCreatedAt(LocalDateTime createdAt);

    Set<Executor> getExecutors();
}