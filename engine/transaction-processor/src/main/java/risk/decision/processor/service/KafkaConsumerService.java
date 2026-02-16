package risk.decision.processor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import risk.decision.common.dto.TransactionRequest;
import risk.decision.processor.client.ProcessingServiceClient;

@Service
public class KafkaConsumerService {

  private final ProcessingServiceClient client;
  private final ObjectMapper mapper = new ObjectMapper();

  private static final Logger log = LoggerFactory.getLogger(
    KafkaConsumerService.class
  );

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

      // Inject transactionId into MDC
      MDC.put("transactionId", request.getTransactionId());

      log.info("Consumed transaction from Kafka");

      client.send(request);

      log.info("Forwarded transaction to processing-service");
    } catch (Exception e) {
      log.error("Error processing Kafka record", e);
      throw e;
    } finally {
      // Always clear MDC
      MDC.clear();
    }
  }
}
