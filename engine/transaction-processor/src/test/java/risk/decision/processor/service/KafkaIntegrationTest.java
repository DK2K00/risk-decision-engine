package risk.decision.processor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KafkaIntegrationTest {

  @Autowired
  private KafkaProducerService producerService;

  @Test
  void testProduceConsume() {
    producerService.send("{\"transactionId\": \"T100\", \"amount\": 300.0}");
  }
}
