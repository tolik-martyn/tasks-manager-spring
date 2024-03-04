package com.example.tasksmanager.model;

/**
 * Класс, представляющий обычную задачу.
 */
public class TaskFactory implements ITaskFactory {
    @Override
    public ITask createTask() {
        return new Task();
    }
}