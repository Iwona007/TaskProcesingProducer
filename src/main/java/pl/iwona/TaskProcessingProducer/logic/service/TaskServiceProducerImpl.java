package pl.iwona.TaskProcessingProducer.logic.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.iwona.TaskProcessingProducer.domain.Task;
import pl.iwona.TaskProcessingProducer.domain.TaskProgress;
import pl.iwona.TaskProcessingProducer.domain.TaskType;
import pl.iwona.TaskProcessingProducer.logic.repository.TaskProducerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceProducerImpl implements TaskServiceProducer {

    private final TaskProducerRepository taskProducerRepository;

    @Override
    public Task createTask(String pattern, String input) {
        var task = createNewTask(pattern, input);
        return taskProducerRepository.save(task);
    }

    @Override
    public List<Task> addTaskToList(String pattern, String input) {
        List<Task> taskList = new ArrayList<>();
        var task = createTask(pattern, input);
        taskList.add(task);
        return taskProducerRepository.saveAll(taskList);
    }

    @Override
    public List<Task> createListTask(List<Task> tasks) {
        final List<Task> taskList = tasks.stream().map(this::buildTaskInList).collect(Collectors.toList());
        return taskProducerRepository.saveAll(taskList);
    }

    public List<Task> createListTaskLoop(List<Task> tasks) {
        for (Task oneTask : tasks) {
            tasks.add(buildTaskInList(oneTask));
        }
        return taskProducerRepository.saveAll(tasks);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskProducerRepository.findAll();
    }

    private Task createNewTask(String pattern, String input) {
        return Task.builder()
                .pattern(pattern)
                .input(input)
                .status(TaskProgress.ZERO.getPercentage())
                .taskType(TaskType.NEW)
                .build();
    }

    private Task buildTaskInList(Task task) {
        return Task
                .builder()
                .taskType(task.getTaskType())
                .pattern(task.getPattern())
                .input(task.getInput())
                .result(task.getResult())
                .status(task.getStatus())
                .build();
    }
}
