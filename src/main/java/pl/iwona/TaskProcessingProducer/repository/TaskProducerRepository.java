package pl.iwona.TaskProcessingProducer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.iwona.TaskProcessingProducer.domain.Task;

public interface TaskProducerRepository extends JpaRepository<Task, Integer> {
}
