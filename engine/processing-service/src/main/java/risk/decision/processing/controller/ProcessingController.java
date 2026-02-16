package risk.decision.processing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
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

  private static final Logger log = LoggerFactory.getLogger(
    ProcessingController.class
  );

  @PostMapping
  public void processTransaction(@RequestBody TransactionRequest request) {
    MDC.put("transactionId", request.getTransactionId());

    try {
      RiskContext context = RiskContextMapper.fromRequest(request, "KAFKA");

      DecisionResult decision = ruleEngine.evaluate(context);

      persistenceService.persist(context, decision);

      log.info("Decision computed: {}", decision.getDecisionType());
    } finally {
      MDC.clear();
    }
  }
}
