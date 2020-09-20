package cours.uahb.repository;

import cours.uahb.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long>
{
    public Operation findAllByRetirerEquals(boolean retirer);
}
