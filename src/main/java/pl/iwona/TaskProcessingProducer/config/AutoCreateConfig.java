package pl.iwona.TaskProcessingProducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("local")
public class AutoCreateConfig {
    @Bean
    public NewTopic taskEvents() {
        return TopicBuilder.name("topics-events")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
