package pl.iwona.TaskProcessingProducer.domain.businesstask;


import lombok.Getter;
import pl.iwona.TaskProcessingProducer.domain.TaskType;

/**
 * Business object Task
 */
@Getter
public class Task {

    private Integer taskId;

    private String input;

    private String pattern;

    private TaskType taskType;

    private String result;

    private String status;
}
