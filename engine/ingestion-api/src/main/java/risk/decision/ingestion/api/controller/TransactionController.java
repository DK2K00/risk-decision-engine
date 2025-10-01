package risk.decision.ingestion.api.controller;

import org.springframework.web.bind.annotation.*;
import risk.decision.common.dto.TransactionRequest;
import risk.decision.common.dto.TransactionResponse;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  @PostMapping
  public TransactionResponse ingestTransaction(
    @RequestBody TransactionRequest request
  ) {
    return new TransactionResponse(
      "APPROVE",
      "Received transaction " +
      request.getTransactionId() +
      " for amount " +
      request.getAmount()
    );
  }
}
