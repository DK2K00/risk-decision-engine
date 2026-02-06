package risk.decision.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "decisions")
public class Decision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionId;
    private String decision;
    private String reason;

    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and setters
}
