package cours.uahb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Operation
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Transaction transaction;
    @Column(length = 100)
    private String type;
    private boolean retirer = false;
    private Date dateRetrait;
    private Date createdAt;

    public Operation()
    {
    }

    public Operation(Transaction transaction, String type, boolean retirer, Date createdAt)
    {
        this.transaction = transaction;
        this.type = type;
        this.retirer = retirer;
        this.dateRetrait = dateRetrait;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Transaction getTransaction()
    {
        return transaction;
    }

    public void setTransaction(Transaction transaction)
    {
        this.transaction = transaction;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public boolean isRetirer()
    {
        return retirer;
    }

    public void setRetirer(boolean retirer)
    {
        this.retirer = retirer;
    }

    public Date getDateRetrait()
    {
        return dateRetrait;
    }

    public void setDateRetrait(Date dateRetrait)
    {
        this.dateRetrait = dateRetrait;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }
}
