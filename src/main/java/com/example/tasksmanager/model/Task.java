package com.example.tasksmanager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс, представляющий обычную задачу.
 */
@Entity
@Table(name = "tasks")
public class Task implements ITask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "task_executors",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "executor_id")
    )
    private Set<Executor> executors;

    protected Task() {
        // Default constructor required by JPA
    }

    private Task(Builder builder) {
        this.description = builder.description;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.executors = builder.executors;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public Set<Executor> getExecutors() {
        return executors;
    }

    public static class Builder {
        private String description;
        private TaskStatus status;
        private LocalDateTime createdAt;
        private final Set<Executor> executors;

        public Builder() {
            this.executors = new HashSet<>();
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder status(TaskStatus status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder addExecutor(Executor executor) {
            this.executors.add(executor);
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}