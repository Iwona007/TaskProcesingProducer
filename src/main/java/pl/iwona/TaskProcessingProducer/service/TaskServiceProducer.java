package pl.iwona.TaskProcessingProducer.service;


import pl.iwona.TaskProcessingProducer.domain.Task;

import java.util.List;

public interface TaskServiceProducer {

    Task createTask(String pattern, String input);

    List<Task> createListTask(String pattern, String input);
}
