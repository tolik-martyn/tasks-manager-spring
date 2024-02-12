package com.example.tasksmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ExecutorService executorService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    public TaskController(TaskService taskService, ExecutorService executorService) {
        this.taskService = taskService;
        this.executorService = executorService;
    }

    @GetMapping("/all")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("formatter", formatter);
        return "task-list";
    }

    @GetMapping("/status/{status}")
    public String getTasksByStatus(@PathVariable("status") TaskStatus status, Model model) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        model.addAttribute("tasks", tasks);
        model.addAttribute("status", status);
        model.addAttribute("formatter", formatter);
        return "view-tasks-by-status";
    }

    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/tasks/all";
    }

    @GetMapping("/{taskId}/update-status")
    public String showUpdateTaskStatusForm(@PathVariable("taskId") Long taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("taskId", taskId);
        model.addAttribute("currentStatus", task.getStatus());
        model.addAttribute("statuses", Arrays.asList(TaskStatus.values()));
        return "update-task-status";
    }

    @PostMapping("/{taskId}/update-status")
    public String updateTaskStatus(@PathVariable("taskId") Long taskId, @RequestParam("status") TaskStatus status) {
        taskService.updateTaskStatus(taskId, status);
        return "redirect:/tasks/all";
    }

    @GetMapping("/{taskId}/delete")
    public String showDeleteTaskForm(@PathVariable("taskId") Long taskId, Model model) {
        model.addAttribute("taskId", taskId);
        return "delete-task";
    }

    @PostMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks/all";
    }

    @PostMapping("/{taskId}/add-executor")
    public String addExecutorToTask(@PathVariable("taskId") Long taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("executors", executorService.getAllExecutors());
        return "add-executor-to-task";
    }

    @PostMapping("/{taskId}/assign-executor")
    public String assignExecutorToTask(@PathVariable("taskId") Long taskId, @RequestParam("executorId") Long executorId) {
        Task task = taskService.getTaskById(taskId);
        Executor executor = executorService.getExecutorById(executorId);
        task.getExecutors().add(executor);
        // taskService.updateTask(task); // Обновляем задачу в БД
        return "redirect:/tasks/all";
    }
}