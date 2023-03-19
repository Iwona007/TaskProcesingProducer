package pl.iwona.TaskProcessingProducer.domain;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskEvent {

//    public static final long serialVersionUID = 1L;

    private Integer taskEventId;

    private TaskType taskType;

    private Task task;
}

