package risk.decision.processing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import risk.decision.persistence.entity.Transaction;

public interface TransactionRepository
  extends JpaRepository<Transaction, Long> {}
