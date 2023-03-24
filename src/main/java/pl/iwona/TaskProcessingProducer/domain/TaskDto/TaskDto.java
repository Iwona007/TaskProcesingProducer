package pl.iwona.TaskProcessingProducer.domain.TaskDto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.iwona.TaskProcessingProducer.domain.TaskType;

//todo ask Marcin
/**
 * Data Transfer Object for business object Task or Test Entity??
 */
@Builder
@Getter
@Setter
public class TaskDto {

    private Integer taskId;

    private String input;

    private String pattern;

    private TaskType taskType;

    private String result;

    private String status;
}
