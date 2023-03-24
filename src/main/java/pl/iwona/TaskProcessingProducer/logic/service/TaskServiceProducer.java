package pl.iwona.TaskProcessingProducer.logic.service;


import pl.iwona.TaskProcessingProducer.domain.Task;

import java.util.List;

public interface TaskServiceProducer {

    Task createTask(String pattern, String input);

    List<Task> addTaskToList(String pattern, String input);

    List<Task> createListTask(List<Task> tasks);

    List<Task> getAllTasks();
}
