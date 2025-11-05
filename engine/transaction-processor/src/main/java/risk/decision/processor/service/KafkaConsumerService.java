package risk.decision.processor.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final String deadLetterTopic;

  public KafkaConsumerService(
    KafkaTemplate<String, String> kafkaTemplate,
    @Value("${topics.dead-letter}") String deadLetterTopic
  ) {
    this.kafkaTemplate = kafkaTemplate;
    this.deadLetterTopic = deadLetterTopic;
  }

  @KafkaListener(topics = "${topics.main}", groupId = "risk-decision-consumer")
  public void consume(ConsumerRecord<String, String> record) {
    try {
      String message = record.value();
      System.out.println("✅ Consumed transaction: " + message);
      // TODO: Call Rule Engine here in future
    } catch (Exception e) {
      System.err.println("❌ Error processing record: " + e.getMessage());
      kafkaTemplate.send(deadLetterTopic, record.value());
    }
  }
}
