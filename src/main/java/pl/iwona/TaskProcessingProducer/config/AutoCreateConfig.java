package pl.iwona.TaskProcessingProducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("dev")
public class AutoCreateConfig {

    private static final Integer PARTITIONS_NUMBER = 3;

    private static final Integer REPLICAS_NUMBER = 1;
    @Bean
    public NewTopic taskEvents() {
        return TopicBuilder.name("topics-events")
                .partitions(PARTITIONS_NUMBER)
                .replicas(REPLICAS_NUMBER)
                .build();
    }
}
