package risk.decision.processing.controller;

import org.springframework.web.bind.annotation.*;
import risk.decision.common.domain.DecisionResult;
import risk.decision.common.domain.DecisionResult;
import risk.decision.common.domain.RiskContext;
import risk.decision.common.dto.TransactionRequest;
import risk.decision.processing.mapper.RiskContextMapper;
import risk.decision.processing.rules.RuleEngine;
import risk.decision.processing.service.DecisionPersistenceService;

@RestController
@RequestMapping("/process")
public class ProcessingController {

  private final RuleEngine ruleEngine;
  private final DecisionPersistenceService persistenceService;

  public ProcessingController(
    RuleEngine ruleEngine,
    DecisionPersistenceService persistenceService
  ) {
    this.ruleEngine = ruleEngine;
    this.persistenceService = persistenceService;
  }

  @PostMapping
  public void processTransaction(@RequestBody TransactionRequest request) {
    RiskContext context = RiskContextMapper.fromRequest(request, "KAFKA");

    DecisionResult decision = ruleEngine.evaluate(context);

    persistenceService.persist(context, decision);

    System.out.println(
      "💾 Persisted decision for txn " + context.getTransactionId()
    );
  }
}
