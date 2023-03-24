package pl.iwona.TaskProcessingProducer.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    private String input;

    private String pattern;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    private String result;

    private String status;
}
