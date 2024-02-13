package com.example.tasksmanager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/tasks")
@Tag(name = "Task Controller", description = "Operations related to tasks")
public class TaskController {

    private final TaskService taskService;
    private final ExecutorService executorService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    public TaskController(TaskService taskService, ExecutorService executorService) {
        this.taskService = taskService;
        this.executorService = executorService;
    }

    @Operation(summary = "Get all tasks", description = "Get a list of all tasks")
    @GetMapping("/all")
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("formatter", formatter);
        return "task-list";
    }

    @Operation(summary = "Get tasks by status", description = "Get a list of tasks by their status")
    @GetMapping("/status/{status}")
    public String getTasksByStatus(@PathVariable("status") TaskStatus status, Model model) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        model.addAttribute("tasks", tasks);
        model.addAttribute("status", status);
        model.addAttribute("formatter", formatter);
        return "view-tasks-by-status";
    }

    @Operation(summary = "Show form to add a task", description = "Display the form to add a new task")
    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @Operation(summary = "Add a task", description = "Add a new task to the system")
    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/tasks/all";
    }

    @Operation(summary = "Show form to update task status", description = "Display the form to update the status of a task")
    @GetMapping("/{taskId}/update-status")
    public String showUpdateTaskStatusForm(@PathVariable("taskId") Long taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("taskId", taskId);
        model.addAttribute("currentStatus", task.getStatus());
        model.addAttribute("statuses", Arrays.asList(TaskStatus.values()));
        return "update-task-status";
    }

    @Operation(summary = "Update task status", description = "Update the status of a task")
    @PostMapping("/{taskId}/update-status")
    public String updateTaskStatus(@PathVariable("taskId") Long taskId, @RequestParam("status") TaskStatus status) {
        taskService.updateTaskStatus(taskId, status);
        return "redirect:/tasks/all";
    }

    @Operation(summary = "Delete a task", description = "Delete a task from the system")
    @GetMapping("/{taskId}/delete")
    public String showDeleteTaskForm(@PathVariable("taskId") Long taskId, Model model) {
        model.addAttribute("taskId", taskId);
        return "delete-task";
    }

    @Operation(summary = "Show form to add executor to a task", description = "Display the form to add an executor to a task")
    @PostMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks/all";
    }

    @Operation(summary = "Show form to add executor to a task", description = "Display the form to add an executor to a task")
    @PostMapping("/{taskId}/add-executor")
    public String addExecutorToTask(@PathVariable("taskId") Long taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("executors", executorService.getAllExecutors());
        return "add-executor-to-task";
    }

    @Operation(summary = "Assign executor to a task", description = "Assign an executor to a task")
    @PostMapping("/{taskId}/assign-executor")
    public String assignExecutorToTask(@PathVariable("taskId") Long taskId, @RequestParam("executorId") Long executorId) {
        Task task = taskService.getTaskById(taskId);
        Executor executor = executorService.getExecutorById(executorId);
        task.getExecutors().add(executor);
        taskService.updateTask(task);
        return "redirect:/tasks/all";
    }
}