package risk.decision.processing.controller;

import org.springframework.web.bind.annotation.*;
import risk.decision.common.dto.TransactionRequest;

@RestController
@RequestMapping("/process")
public class ProcessingController {

  @PostMapping
  public void processTransaction(@RequestBody TransactionRequest request) {
    System.out.println(
      "🧠 Processing transaction: " + request.getTransactionId()
    );
    // TODO:
    // 1. Map to RiskContext
    // 2. Enrich
    // 3. Call rule engine
    // 4. Persist decision
  }
}
