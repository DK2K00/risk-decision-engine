package risk.decision.common.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RiskContext {

  /* =========================
       1. Identifiers / Tracing
       ========================= */

  private String transactionId;
  private String accountId;
  private Instant receivedAt;
  private String source; // API | TCP | KAFKA

  /* =========================
       2. Raw Transaction Data
       ========================= */

  private BigDecimal amount;
  private String currency;
  private Instant transactionTime;

  /* =========================
       3. Enriched Data
       ========================= */

  private Integer transactionsLastHour;
  private Integer transactionsLastDay;
  private String customerRiskTier; // LOW / MEDIUM / HIGH
  private Boolean accountActive;
  private Double externalFraudScore;

  /* =========================
       4. Derived Variables
       ========================= */

  private Integer velocityScore;
  private Double aggregatedRiskScore;

  /* =========================
       5. Decision Output
       ========================= */

  private DecisionResult decisionResult;

  /* =========================
       6. Rule Audit Metadata
       ========================= */

  private final Set<String> triggeredRules = new HashSet<>();

  /* =========================
       Helper Methods (for rules)
       ========================= */

  public void addTriggeredRule(String ruleId) {
    triggeredRules.add(ruleId);
  }

  public Set<String> getTriggeredRules() {
    return Collections.unmodifiableSet(triggeredRules);
  }

  public boolean isDecided() {
    return decisionResult != null;
  }

  public void approve(String reason) {
    this.decisionResult = DecisionResult.approve(reason);
  }

  public void decline(String reason) {
    this.decisionResult = DecisionResult.decline(reason);
  }

  public void review(String reason) {
    this.decisionResult = DecisionResult.review(reason);
  }

  /* =========================
       Getters & Setters
       ========================= */

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public Instant getReceivedAt() {
    return receivedAt;
  }

  public void setReceivedAt(Instant receivedAt) {
    this.receivedAt = receivedAt;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Instant getTransactionTime() {
    return transactionTime;
  }

  public void setTransactionTime(Instant transactionTime) {
    this.transactionTime = transactionTime;
  }

  public Integer getTransactionsLastHour() {
    return transactionsLastHour;
  }

  public void setTransactionsLastHour(Integer transactionsLastHour) {
    this.transactionsLastHour = transactionsLastHour;
  }

  public Integer getTransactionsLastDay() {
    return transactionsLastDay;
  }

  public void setTransactionsLastDay(Integer transactionsLastDay) {
    this.transactionsLastDay = transactionsLastDay;
  }

  public String getCustomerRiskTier() {
    return customerRiskTier;
  }

  public void setCustomerRiskTier(String customerRiskTier) {
    this.customerRiskTier = customerRiskTier;
  }

  public Boolean getAccountActive() {
    return accountActive;
  }

  public void setAccountActive(Boolean accountActive) {
    this.accountActive = accountActive;
  }

  public Double getExternalFraudScore() {
    return externalFraudScore;
  }

  public void setExternalFraudScore(Double externalFraudScore) {
    this.externalFraudScore = externalFraudScore;
  }

  public Integer getVelocityScore() {
    return velocityScore;
  }

  public void setVelocityScore(Integer velocityScore) {
    this.velocityScore = velocityScore;
  }

  public Double getAggregatedRiskScore() {
    return aggregatedRiskScore;
  }

  public void setAggregatedRiskScore(Double aggregatedRiskScore) {
    this.aggregatedRiskScore = aggregatedRiskScore;
  }

  public DecisionResult getDecisionResult() {
    return decisionResult;
  }
}
