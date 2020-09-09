package cours.uahb.repository;

import cours.uahb.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUtilisateur extends JpaRepository<Utilisateur,Integer>
{
    public Utilisateur findByLogin(String email);
    public Utilisateur findByCode(String code);
//    public Utilisateur findBy(String code);
}
