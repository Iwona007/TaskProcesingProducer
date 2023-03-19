package pl.iwona.TaskProcessingProducer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.iwona.TaskProcessingProducer.domain.Task;
import pl.iwona.TaskProcessingProducer.domain.TaskType;
import pl.iwona.TaskProcessingProducer.repository.TaskProducerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class TaskServiceImpl {

    @Autowired
    private TaskProducerRepository taskProducerRepository;

    public Task createTask(String pattern, String input) {
         Task task = Task.builder()
                 .pattern(pattern)
                 .input(input)
                 .taskType(TaskType.NEW)
                 .build();
        return task;
    }

    public Task saveTask(String pattern, String input) {
       return taskProducerRepository.save(createTask(pattern, input));
    }

    public List<Task> createListTask(String pattern, String input) {
        List<Task> taskList = new ArrayList<>();
        Task task = createTask(pattern, input);
        taskList.add(task);
        return taskList;
    }

    public void saveTaskList(String pattern, String input) {
        taskProducerRepository.saveAll(createListTask(pattern, input));
    }
    public Optional<Task> findTaskById(Integer taskId) {

        return Optional.empty();
    }
}
