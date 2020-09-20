package cours.uahb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Gain
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Utilisateur caissier1;
    @ManyToOne(fetch = FetchType.EAGER)
    private Utilisateur caissier2;
    private double systeme;
    private double gainCaissier1;
    private double gainCaissier2;
    private double etat;
    private Date createdAt;

    public Gain()
    {
    }

    public Gain(Utilisateur caissier1, Utilisateur caissier2, double systeme, double gainCaissier1, double gainCaissier2, double etat, Date createdAt)
    {
        this.caissier1 = caissier1;
        this.caissier2 = caissier2;
        this.systeme = systeme;
        this.gainCaissier1 = gainCaissier1;
        this.gainCaissier2 = gainCaissier2;
        this.etat = etat;
        this.createdAt = createdAt;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Utilisateur getCaissier1()
    {
        return caissier1;
    }

    public void setCaissier1(Utilisateur caissier1)
    {
        this.caissier1 = caissier1;
    }

    public Utilisateur getCaissier2()
    {
        return caissier2;
    }

    public void setCaissier2(Utilisateur caissier2)
    {
        this.caissier2 = caissier2;
    }

    public double getSysteme()
    {
        return systeme;
    }

    public void setSysteme(double systeme)
    {
        this.systeme = systeme;
    }

    public double getGainCaissier1()
    {
        return gainCaissier1;
    }

    public void setGainCaissier1(double gainCaissier1)
    {
        this.gainCaissier1 = gainCaissier1;
    }

    public double getGainCaissier2()
    {
        return gainCaissier2;
    }

    public void setGainCaissier2(double gainCaissier2)
    {
        this.gainCaissier2 = gainCaissier2;
    }

    public double getEtat()
    {
        return etat;
    }

    public void setEtat(double etat)
    {
        this.etat = etat;
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
