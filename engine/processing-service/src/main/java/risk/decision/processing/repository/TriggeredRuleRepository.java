package risk.decision.processing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import risk.decision.persistence.entity.TriggeredRule;

public interface TriggeredRuleRepository
        extends JpaRepository<TriggeredRule, Long> {
}
