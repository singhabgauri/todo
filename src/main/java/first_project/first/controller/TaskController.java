package first_project.first.controller;

import first_project.first.model.Task;
import first_project.first.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks(Authentication authentication) {
        return taskRepository.findByUserId(authentication.getName());
    }

    @PostMapping
    public Task addTask(@RequestBody Task task, Authentication authentication) {
        task.setUserId(authentication.getName());
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id) {
        taskRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Task> updateTask(
            @PathVariable String id,
            @RequestBody Task task,
            Authentication authentication) {
        
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!existingTask.getUserId().equals(authentication.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        existingTask.setDescription(task.getDescription());
        Task updatedTask = taskRepository.save(existingTask);
        return ResponseEntity.ok(updatedTask);
    }
}
