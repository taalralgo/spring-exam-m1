package cours.uahb.repository;

import cours.uahb.model.Operation;
import cours.uahb.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long>
{
}

