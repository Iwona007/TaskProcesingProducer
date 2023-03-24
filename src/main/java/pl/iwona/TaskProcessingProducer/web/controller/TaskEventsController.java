package pl.iwona.TaskProcessingProducer.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.iwona.TaskProcessingProducer.domain.TaskDto.TaskDto;
import pl.iwona.TaskProcessingProducer.domain.entity.TaskEntity;
import pl.iwona.TaskProcessingProducer.domain.mapper.TaskMapper;
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

    private final TaskMapper taskMapper;

    @PostMapping("/task")
    public ResponseEntity<TaskDto> createTask(@NotBlank @RequestParam String pattern, @NotBlank @RequestParam String input)
            throws JsonProcessingException {
        log.info("before sending task event");
        final TaskDto taskDto = taskMapper.mapTaskDtoToTaskEntity(this.taskService.createTask(pattern, input));
        taskEventProducer.sendTaskEvent(taskDto);
        log.info("after sending task event {}: ", taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @PostMapping("/task/list")
//    public ResponseEntity<List<TaskDto>> addTaskToList(@NotBlank @RequestParam String pattern,
//                                                          @NotBlank @RequestParam String input) {
//        taskMapper.mapTaskDtoListToTaskEntityList(this.taskService.addTaskToList(pattern, input));
//        return new ResponseEntity<>(tasks, HttpStatus.CREATED);
//    }

    //todo buch to create a hundreds Tasks
    @PostMapping("/tasks")
    public ResponseEntity<List<TaskDto>> createListTask(@NotBlank @RequestBody List<TaskEntity> taskEntities) {
        //buch obiekt lista
        this.taskService.createListTask(taskEntities);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        final List<TaskDto> taskDtos = taskMapper.mapTaskDtoListToTaskEntityList(this.taskService.getAllTasks());
        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }
}
