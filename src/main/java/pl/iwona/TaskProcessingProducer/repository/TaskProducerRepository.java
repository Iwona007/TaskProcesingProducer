package pl.iwona.TaskProcessingProducer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.iwona.TaskProcessingProducer.domain.Task;

@Repository
public interface TaskProducerRepository extends JpaRepository<Task, Integer> {
}
