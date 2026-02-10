package risk.decision.processing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import risk.decision.persistence.entity.Decision;

public interface DecisionRepository extends JpaRepository<Decision, Long> {}
