package com.example.tasksmanager.model;

public class UrgentTaskFactory implements TaskFactory {
    @Override
    public ITask createTask() {
        return new UrgentITask();
    }
}