package risk.decision.processing.controller;

import org.springframework.web.bind.annotation.*;
import risk.decision.common.domain.DecisionResult;
import risk.decision.common.domain.RiskContext;
import risk.decision.common.dto.TransactionRequest;
import risk.decision.processing.mapper.RiskContextMapper;
import risk.decision.processing.rules.RuleEngine;

@RestController
@RequestMapping("/process")
public class ProcessingController {

  private final RuleEngine ruleEngine;

  public ProcessingController(RuleEngine ruleEngine) {
    this.ruleEngine = ruleEngine;
  }

  @PostMapping
  public void processTransaction(@RequestBody TransactionRequest request) {
    RiskContext context = RiskContextMapper.fromRequest(request, "KAFKA");

    DecisionResult decision = ruleEngine.evaluate(context);

    System.out.println(
      "✅ Decision for txn " +
      context.getTransactionId() +
      ": " +
      decision.getDecisionType() +
      " (" +
      decision.getReason() +
      ")"
    );
    // TODO next:
    // persist decision
    // publish decision event
    // log to ELK
  }
}
