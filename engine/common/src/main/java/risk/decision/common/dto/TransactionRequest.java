package risk.decision.common.dto;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;

@Data
public class TransactionRequest {

  private String transactionId;

  private String accountId;

  private BigDecimal amount;
  private String currency;
  private Instant transactionTime;
}
