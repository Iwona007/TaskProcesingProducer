package pl.iwona.TaskProcessingProducer.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.iwona.TaskProcessingProducer.domain.Task;
import pl.iwona.TaskProcessingProducer.logic.service.TaskServiceProducer;
import pl.iwona.TaskProcessingProducer.producer.TaskEventProducer;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/app")
public class TaskEventsController {

    private final TaskServiceProducer taskService;

    private final TaskEventProducer taskEventProducer;
    @Autowired
    public TaskEventsController(TaskServiceProducer taskService, TaskEventProducer taskEventProducer) {
        this.taskService = taskService;
        this.taskEventProducer = taskEventProducer;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@NotBlank  @RequestParam String pattern, @NotBlank @RequestParam String input)
            throws JsonProcessingException {
        log.info("before sending task event");
        final Task task = this.taskService.createTask(pattern, input);
        taskEventProducer.sendTaskEvent(task);
        log.info("after sending task event {}: ", task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/taskslist")
    public ResponseEntity<List<Task>> createListTask(@NotBlank @RequestParam String pattern, @NotBlank @RequestParam String input) {
        var task = this.taskService.createTask(pattern, input);
        this.taskService.createListTask(task.getPattern(), task.getInput());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

