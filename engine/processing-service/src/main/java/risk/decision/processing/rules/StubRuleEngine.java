package risk.decision.processing.rules;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import risk.decision.common.domain.DecisionResult;
import risk.decision.common.domain.DecisionType;
import risk.decision.common.domain.RiskContext;

@Component
public class StubRuleEngine implements RuleEngine {

  private static final BigDecimal HIGH_AMOUNT = new BigDecimal("10000");

  @Override
  public DecisionResult evaluate(RiskContext ctx) {
    // Example rule 1: account inactive
    if (Boolean.FALSE.equals(ctx.getAccountActive())) {
      ctx.addTriggeredRule("RULE_ACCOUNT_INACTIVE");
      return DecisionResult.decline("Account is inactive");
    }

    // Example rule 2: high transaction amount
    if (ctx.getAmount() != null && ctx.getAmount().compareTo(HIGH_AMOUNT) > 0) {
      ctx.addTriggeredRule("RULE_HIGH_AMOUNT");
      return DecisionResult.decline("Transaction amount exceeds threshold");
    }

    // Default approve
    ctx.addTriggeredRule("RULE_DEFAULT_APPROVE");
    return DecisionResult.approve("Approved by default stub rule");
  }
}
