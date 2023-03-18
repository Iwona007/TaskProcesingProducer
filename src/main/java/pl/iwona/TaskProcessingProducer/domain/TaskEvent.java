package pl.iwona.TaskProcessingProducer.domain;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class TaskEvent implements Serializable {

    public static final long serialVersionUID = 1L;


    private Integer taskEventId;

    private TaskEventType taskEventType;

    private Task task;

    public TaskEvent(TaskEventType taskEventType, Task task) {
        this.taskEventType = taskEventType;
        this.task = task;
    }
}

