package com.example.tasksmanager.model;

public class UrgentTaskFactory implements ITaskFactory {
    @Override
    public ITask createTask() {
        return new UrgentTask();
    }
}