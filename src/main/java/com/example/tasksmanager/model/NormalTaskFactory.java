package com.example.tasksmanager.model;

/**
 * Класс, представляющий обычную задачу.
 */
public class NormalTaskFactory implements TaskFactory {
    @Override
    public ITask createTask() {
        return new Task();
    }
}