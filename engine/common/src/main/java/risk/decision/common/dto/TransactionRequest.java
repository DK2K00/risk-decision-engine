package risk.decision.common.dto;

import lombok.Data;

@Data
public class TransactionRequest {
    private String transactionId;
    private double amount;
    private String accountId;
}
