package pl.iwona.TaskProcessingProducer.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Task {

//    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    private String input;

    private String pattern;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    private String result;

    private String status;

//    private TaskEvent taskEvent;
}
