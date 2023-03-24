package pl.iwona.TaskProcessingProducer.logic.service;


import pl.iwona.TaskProcessingProducer.domain.entity.TaskEntity;

import java.util.List;

public interface TaskServiceProducer {

    TaskEntity createTask(String pattern, String input);

    List<TaskEntity> addTaskToList(String pattern, String input);

    List<TaskEntity> createListTask(List<TaskEntity> taskEntities);

    List<TaskEntity> getAllTasks();
}
