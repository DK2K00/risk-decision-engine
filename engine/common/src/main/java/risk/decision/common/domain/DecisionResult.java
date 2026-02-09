package risk.decision.common.domain;

public class DecisionResult {

  private final DecisionType decisionType;
  private final String reason;

  private DecisionResult(DecisionType decisionType, String reason) {
    this.decisionType = decisionType;
    this.reason = reason;
  }

  public static DecisionResult approve(String reason) {
    return new DecisionResult(DecisionType.APPROVE, reason);
  }

  public static DecisionResult decline(String reason) {
    return new DecisionResult(DecisionType.DECLINE, reason);
  }

  public static DecisionResult review(String reason) {
    return new DecisionResult(DecisionType.REVIEW, reason);
  }

  public DecisionType getDecisionType() {
    return decisionType;
  }

  public String getReason() {
    return reason;
  }
}
