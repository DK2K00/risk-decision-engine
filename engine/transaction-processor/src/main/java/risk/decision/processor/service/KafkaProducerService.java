package risk.decision.processor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//This file for testing purposes

@Service
public class KafkaProducerService {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final String topicName;

  public KafkaProducerService(
    KafkaTemplate<String, String> kafkaTemplate,
    @Value("${topics.main}") String topicName
  ) {
    this.kafkaTemplate = kafkaTemplate;
    this.topicName = topicName;
  }

  public void send(String message) {
    kafkaTemplate.send(topicName, message);
    System.out.println("📤 Produced message: " + message);
  }
}
