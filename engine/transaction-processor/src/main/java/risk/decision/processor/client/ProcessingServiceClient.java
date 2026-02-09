package risk.decision.processor.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import risk.decision.common.dto.TransactionRequest;

@Component
public class ProcessingServiceClient {

    private final WebClient webClient;

    public ProcessingServiceClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8081") // processing-service port
                .build();
    }

    public void send(TransactionRequest request) {
        webClient.post()
                .uri("/process")
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity()
                .block(); // OK for now, async later
    }
}
