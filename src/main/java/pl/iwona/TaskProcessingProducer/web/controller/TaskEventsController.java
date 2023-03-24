package pl.iwona.TaskProcessingProducer.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.iwona.TaskProcessingProducer.domain.Task;
import pl.iwona.TaskProcessingProducer.logic.service.TaskServiceProducer;
import pl.iwona.TaskProcessingProducer.producer.TaskEventProducer;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class TaskEventsController {

    private final TaskServiceProducer taskService;

    private final TaskEventProducer taskEventProducer;

    @PostMapping("/task")
    public ResponseEntity<Task> createTask(@NotBlank @RequestParam String pattern, @NotBlank @RequestParam String input)
            throws JsonProcessingException {
        log.info("before sending task event");
        var task = this.taskService.createTask(pattern, input);
        taskEventProducer.sendTaskEvent(task);
        log.info("after sending task event {}: ", task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/task/list")
    public ResponseEntity<List<Task>> addTaskToList(@NotBlank @RequestParam String pattern,
                                                    @NotBlank @RequestParam String input) {
        var tasks = this.taskService.addTaskToList(pattern, input);
        return new ResponseEntity<>(tasks, HttpStatus.CREATED);
    }

    //todo buch to create a hundreds Tasks
    @PostMapping("/tasks")
    public ResponseEntity<List<Task>> createListTask(@NotBlank @RequestBody List<Task> tasks) {
        //buch obiekt lista
        this.taskService.createListTask(tasks);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        final List<Task> allTasks = this.taskService.getAllTasks();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }
}
