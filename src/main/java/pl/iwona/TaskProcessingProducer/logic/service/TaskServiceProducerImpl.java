package pl.iwona.TaskProcessingProducer.logic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.iwona.TaskProcessingProducer.domain.Task;
import pl.iwona.TaskProcessingProducer.domain.TaskProgress;
import pl.iwona.TaskProcessingProducer.domain.TaskType;
import pl.iwona.TaskProcessingProducer.logic.repository.TaskProducerRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TaskServiceProducerImpl implements TaskServiceProducer {

    private final TaskProducerRepository taskProducerRepository;
    @Autowired
    public TaskServiceProducerImpl(TaskProducerRepository taskProducerRepository) {
        this.taskProducerRepository = taskProducerRepository;
    }

    @Override
    public Task createTask(String pattern, String input) {
            var task = Task.builder()
                    .pattern(pattern)
                    .input(input)
                    .status(TaskProgress.ZERO.getPercentage())
                    .taskType(TaskType.NEW)
                    .build();
            return taskProducerRepository.save(task);
        }

    @Override
    public List<Task> createListTask(String pattern, String input) {
        List<Task> taskList = new ArrayList<>();
        Task task = createTask(pattern, input);
        taskList.add(task);
        return taskProducerRepository.saveAll(taskList);
    }
}
