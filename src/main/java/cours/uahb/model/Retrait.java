package cours.uahb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Retrait
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Utilisateur caissier;
    @ManyToOne(fetch = FetchType.EAGER)
    private Transaction transaction;
    private Date dateTretrait = new Date();

    public Retrait()
    {
    }

    public Retrait(Utilisateur caissier, Transaction transaction, Date dateTretrait)
    {
        this.caissier = caissier;
        this.transaction = transaction;
        this.dateTretrait = dateTretrait;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Utilisateur getCaissier()
    {
        return caissier;
    }

    public void setCaissier(Utilisateur caissier)
    {
        this.caissier = caissier;
    }

    public Transaction getTransaction()
    {
        return transaction;
    }

    public void setTransaction(Transaction transaction)
    {
        this.transaction = transaction;
    }

    public Date getDateTretrait()
    {
        return dateTretrait;
    }

    public void setDateTretrait(Date dateTretrait)
    {
        this.dateTretrait = dateTretrait;
    }
}
