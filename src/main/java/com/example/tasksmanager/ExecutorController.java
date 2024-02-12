package com.example.tasksmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/executors")
public class ExecutorController {

    private final ExecutorService executorService;

    @Autowired
    public ExecutorController(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @GetMapping("/all")
    public String getAllExecutors(Model model) {
        List<Executor> executors = executorService.getAllExecutors();
        model.addAttribute("executors", executors);
        return "executor-list";
    }

    @GetMapping("/add")
    public String showAddExecutorForm(Model model) {
        model.addAttribute("executor", new Executor());
        return "add-executor";
    }

    @PostMapping("/add")
    public String addExecutor(@ModelAttribute Executor executor) {
        executorService.addExecutor(executor);
        return "redirect:/executors/all";
    }

    @GetMapping("/{executorId}/update")
    public String showUpdateExecutorForm(@PathVariable("executorId") Long executorId, Model model) {
        Executor executor = executorService.getExecutorById(executorId);
        model.addAttribute("executor", executor);
        return "update-executor";
    }

    @PostMapping("/{executorId}/update")
    public String updateExecutor(@PathVariable("executorId") Long executorId, @ModelAttribute Executor updatedExecutor) {
        executorService.updateExecutor(executorId, updatedExecutor);
        return "redirect:/executors/all";
    }

    @GetMapping("/{executorId}/delete")
    public String showDeleteExecutorForm(@PathVariable("executorId") Long executorId, Model model) {
        model.addAttribute("executorId", executorId);
        return "delete-executor";
    }

    @PostMapping("/{executorId}/delete")
    public String deleteExecutor(@PathVariable("executorId") Long executorId) {
        executorService.deleteExecutor(executorId);
        return "redirect:/executors/all";
    }
}