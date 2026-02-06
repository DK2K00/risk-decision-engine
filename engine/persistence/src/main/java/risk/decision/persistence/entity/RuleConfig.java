package risk.decision.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rules_config")
public class RuleConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;
    private String expression;
    private boolean active;
}
