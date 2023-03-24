package pl.iwona.TaskProcessingProducer.logic.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.iwona.TaskProcessingProducer.domain.entity.TaskEntity;

@Repository
public interface TaskProducerRepository extends JpaRepository<TaskEntity, Integer> {
}