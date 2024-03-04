package com.example.tasksmanager.model;

public class TaskFactory implements ITaskFactory {
    @Override
    public ITask createTask() {
        return new Task();
    }
}