package pl.iwona.TaskProcessingProducer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.iwona.TaskProcessingProducer.domain.Task;
import pl.iwona.TaskProcessingProducer.domain.TaskEvent;
import pl.iwona.TaskProcessingProducer.producer.TaskEventProducer;
import pl.iwona.TaskProcessingProducer.service.TaskServiceImpl;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/app")
public class TaskEventsController {

    private TaskServiceImpl taskService;

    private TaskEventProducer taskEventProducer;
    @Autowired
    public TaskEventsController(TaskServiceImpl taskService, TaskEventProducer taskEventProducer) {
        this.taskService = taskService;
        this.taskEventProducer = taskEventProducer;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestParam String pattern, @RequestParam String input)
            throws JsonProcessingException {
        log.info("before sending task event");
        final Task task = this.taskService.saveTask(pattern, input);
        taskEventProducer.sendTaskEvent(task);
        log.info("after sending task event {}: ", task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/taskslist")
    public ResponseEntity<List<TaskEvent>> createListTask(@RequestParam String pattern, @RequestParam String input)
            throws JsonProcessingException {
        var task = this.taskService.createTask(pattern, input);
        this.taskService.createListTask(task.getPattern(), task.getInput());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Optional<Task>> findTaskById(@PathVariable Integer taskId) {
        final Optional<Task> task = Optional.ofNullable(this.taskService.findTaskById(taskId));
        return new ResponseEntity<>(task, HttpStatus.FOUND);
    }

//    @GetMapping("/tasks")
//    public ResponseEntity<List<Task>> getListTask() {
//        final List<Task> allTask = this.taskService.getAllTask();
//        return new ResponseEntity<>(allTask, HttpStatus.FOUND);
//    }
//
//    @GetMapping("/tasks/{taskId}")
//    public String readStatusAndResult(Integer taskId) {
//        return this.taskService.readStatusAndResult(taskId);
//    }
}


