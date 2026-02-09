package risk.decision.processing.mapper;

import java.time.Instant;
import risk.decision.common.domain.RiskContext;
import risk.decision.common.dto.TransactionRequest;

public class RiskContextMapper {

  private RiskContextMapper() {
    // utility class
  }

  public static RiskContext fromRequest(
    TransactionRequest request,
    String source
  ) {
    RiskContext ctx = new RiskContext();

    // =========================
    // Identifiers & tracing
    // =========================
    ctx.setTransactionId(request.getTransactionId());
    ctx.setAccountId(request.getAccountId());
    ctx.setSource(source);
    ctx.setReceivedAt(Instant.now());

    // =========================
    // Raw transaction data
    // =========================
    ctx.setAmount(request.getAmount()); // assuming BigDecimal now
    ctx.setCurrency(request.getCurrency());
    ctx.setTransactionTime(request.getTransactionTime());

    // =========================
    // Safe defaults (important)
    // =========================
    ctx.setTransactionsLastHour(0);
    ctx.setTransactionsLastDay(0);
    ctx.setAccountActive(true);
    ctx.setExternalFraudScore(null);
    ctx.setVelocityScore(0);
    ctx.setAggregatedRiskScore(0.0);

    return ctx;
  }
}
