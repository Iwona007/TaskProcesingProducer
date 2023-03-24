package pl.iwona.TaskProcessingProducer.logic.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.iwona.TaskProcessingProducer.domain.entity.TaskEntity;
import pl.iwona.TaskProcessingProducer.domain.TaskProgress;
import pl.iwona.TaskProcessingProducer.domain.TaskType;
import pl.iwona.TaskProcessingProducer.domain.mapper.TaskMapper;
import pl.iwona.TaskProcessingProducer.logic.repository.TaskProducerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceProducerImpl implements TaskServiceProducer {

    private final TaskProducerRepository taskProducerRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskEntity createTask(String pattern, String input) {
        var task = createNewTask(pattern, input);
        return taskProducerRepository.save(task);
    }

    @Override
    public List<TaskEntity> addTaskToList(String pattern, String input) {
        List<TaskEntity> taskEntityList = new ArrayList<>();
        var task = createTask(pattern, input);
        taskEntityList.add(task);
        return taskProducerRepository.saveAll(taskEntityList);
    }

    @Override
    public List<TaskEntity> createListTask(List<TaskEntity> taskEntities) {
        final List<TaskEntity> taskEntityList = taskEntities.stream().map(this::buildTaskInList).collect(Collectors.toList());
        return taskProducerRepository.saveAll(taskEntityList);
    }

    public List<TaskEntity> createListTaskLoop(List<TaskEntity> taskEntities) {
        for (TaskEntity oneTaskEntity : taskEntities) {
            taskEntities.add(buildTaskInList(oneTaskEntity));
        }
        return taskProducerRepository.saveAll(taskEntities);
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskProducerRepository.findAll();
    }

    private TaskEntity createNewTask(String pattern, String input) {
        return TaskEntity.builder()
                .pattern(pattern)
                .input(input)
                .status(TaskProgress.ZERO.getPercentage())
                .taskType(TaskType.NEW)
                .build();
    }

    private TaskEntity buildTaskInList(TaskEntity taskEntity) {
        return TaskEntity
                .builder()
                .taskType(taskEntity.getTaskType())
                .pattern(taskEntity.getPattern())
                .input(taskEntity.getInput())
                .result(taskEntity.getResult())
                .status(taskEntity.getStatus())
                .build();
    }
}
