package com.example.tasksmanager.config;

import com.example.tasksmanager.model.TaskFactory;
import com.example.tasksmanager.model.UrgentTaskFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UrgentTaskFactory urgentTaskFactory() {
        return new UrgentTaskFactory();
    }

    @Bean
    public TaskFactory normalTaskFactory() {
        return new TaskFactory();
    }
}