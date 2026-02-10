package risk.decision.processing.service;

import org.springframework.stereotype.Service;
import risk.decision.common.domain.DecisionResult;
import risk.decision.common.domain.RiskContext;
import risk.decision.persistence.entity.Decision;
import risk.decision.persistence.entity.Transaction;
import risk.decision.processing.repository.DecisionRepository;
import risk.decision.processing.repository.TransactionRepository;

@Service
public class DecisionPersistenceService {

  private final TransactionRepository transactionRepository;
  private final DecisionRepository decisionRepository;

  public DecisionPersistenceService(
    TransactionRepository transactionRepository,
    DecisionRepository decisionRepository
  ) {
    this.transactionRepository = transactionRepository;
    this.decisionRepository = decisionRepository;
  }

  public void persist(RiskContext context, DecisionResult result) {
    Transaction txn = new Transaction();
    txn.setTransactionId(context.getTransactionId());
    txn.setAmount(context.getAmount().doubleValue());
    txn.setStatus(result.getDecisionType().name());

    transactionRepository.save(txn);

    Decision decision = new Decision();
    decision.setTransactionId(context.getTransactionId());
    decision.setDecision(result.getDecisionType().name());
    decision.setReason(result.getReason());

    decisionRepository.save(decision);
  }
}
