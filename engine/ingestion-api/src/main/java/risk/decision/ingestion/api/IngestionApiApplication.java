package risk.decision.ingestion.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import risk.decision.common.dto.TransactionRequest;

@SpringBootApplication
@RestController
@RequestMapping("/api/transactions")
public class IngestionApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngestionApiApplication.class, args);
    }

    @PostMapping
    public String ingestTransaction(@RequestBody TransactionRequest request) {
        return "Received transaction " + request.getTransactionId() + " for amount " + request.getAmount();
    }
}
