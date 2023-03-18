package pl.iwona.TaskProcessingProducer.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Task implements Serializable {

    public static final long serialVersionUID = 1L;


    private Integer taskId;

    private String input;

    private String pattern;

    private String result;

    private String status;

    private TaskEvent taskEvent;

    public Task(String input, String pattern, String result, String status) {
        this.input = input;
        this.pattern = pattern;
        this.result = result;
        this.status = status;
    }
}
