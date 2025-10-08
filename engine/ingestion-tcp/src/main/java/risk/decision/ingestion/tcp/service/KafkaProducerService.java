package risk.decision.ingestion.tcp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate,
                                @Value("${kafka.topic.transactions}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void sendTransaction(String message) {
        kafkaTemplate.send(topicName, message);
    }
}
