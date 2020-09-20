package cours.uahb.repository;

import cours.uahb.model.Retrait;
import cours.uahb.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetraitRepository extends JpaRepository<Retrait,Long>
{
    public List<Retrait> findAllByCaissier(Utilisateur caissier);
}
