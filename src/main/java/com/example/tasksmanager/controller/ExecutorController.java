package com.example.tasksmanager.controller;

import com.example.tasksmanager.service.ExecutorService;
import com.example.tasksmanager.entity.Executor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/executors")
@Tag(name = "Executor Controller", description = "Operations related to executors")
public class ExecutorController {

    private final ExecutorService executorService;

    @Autowired
    public ExecutorController(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Operation(summary = "Get all executors", description = "Get a list of all executors")
    @GetMapping("/all")
    public String getAllExecutors(Model model) {
        List<Executor> executors = executorService.getAllExecutors();
        model.addAttribute("executors", executors);
        return "executor-list";
    }

    @Operation(summary = "Show form to add an executor", description = "Display the form to add a new executor")
    @GetMapping("/add")
    public String showAddExecutorForm(Model model) {
        model.addAttribute("executor", new Executor());
        return "add-executor";
    }

    @Operation(summary = "Add an executor", description = "Add a new executor to the system")
    @PostMapping("/add")
    public String addExecutor(@ModelAttribute Executor executor) {
        executorService.addExecutor(executor);
        return "redirect:/executors/all";
    }

    @Operation(summary = "Show form to update an executor", description = "Display the form to update an existing executor")
    @GetMapping("/{executorId}/update")
    public String showUpdateExecutorForm(@PathVariable("executorId") Long executorId, Model model) {
        Executor executor = executorService.getExecutorById(executorId);
        model.addAttribute("executor", executor);
        return "update-executor";
    }

    @Operation(summary = "Update an executor", description = "Update an existing executor in the system")
    @PostMapping("/{executorId}/update")
    public String updateExecutor(@PathVariable("executorId") Long executorId, @ModelAttribute Executor updatedExecutor) {
        executorService.updateExecutor(executorId, updatedExecutor);
        return "redirect:/executors/all";
    }

    @Operation(summary = "Show form to delete an executor", description = "Display the form to delete an executor")
    @GetMapping("/{executorId}/delete")
    public String showDeleteExecutorForm(@PathVariable("executorId") Long executorId, Model model) {
        model.addAttribute("executorId", executorId);
        return "delete-executor";
    }

    @Operation(summary = "Delete an executor", description = "Delete an executor from the system")
    @PostMapping("/{executorId}/delete")
    public String deleteExecutor(@PathVariable("executorId") Long executorId) {
        executorService.deleteExecutor(executorId);
        return "redirect:/executors/all";
    }
}