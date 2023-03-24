package pl.iwona.TaskProcessingProducer.domain.mapper;

import org.mapstruct.Mapper;

import pl.iwona.TaskProcessingProducer.domain.TaskDto.TaskDto;
import pl.iwona.TaskProcessingProducer.domain.entity.TaskEntity;

import java.util.List;

/**
 * Mapper for data transfer object and task entity
 */
@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto mapTaskDtoToTaskEntity(TaskEntity task);

    List<TaskDto> mapTaskDtoListToTaskEntityList(List<TaskEntity> allTasks);
}
