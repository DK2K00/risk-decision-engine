package risk.decision.processor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import risk.decision.common.dto.TransactionRequest;
import risk.decision.processor.client.ProcessingServiceClient;

@Service
public class KafkaConsumerService {

  private final ProcessingServiceClient client;
  private final ObjectMapper mapper = new ObjectMapper();

  public KafkaConsumerService(ProcessingServiceClient client) {
    this.client = client;
  }

  @KafkaListener(topics = "${topics.main}", groupId = "risk-decision-consumer")
  public void consume(ConsumerRecord<String, String> record) throws Exception {
    try {
      TransactionRequest request = mapper.readValue(
        record.value(),
        TransactionRequest.class
      );

      System.out.println(
        "📥 Forwarding transaction to processing-service: " +
        request.getTransactionId()
      );

      client.send(request);
    } catch (Exception e) {
      System.err.println("❌ Failed to process record: " + e.getMessage());
      throw e; // let Kafka retry for now
    }
  }
}
