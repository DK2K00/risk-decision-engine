package risk.decision.processing.rules;

import risk.decision.common.domain.DecisionResult;
import risk.decision.common.domain.RiskContext;

public interface RuleEngine {
  DecisionResult evaluate(RiskContext context);
}
