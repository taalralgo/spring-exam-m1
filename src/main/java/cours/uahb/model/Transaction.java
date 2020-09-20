package cours.uahb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String numeroPiece;
    @Column(length = 255, nullable = false)
    private String code;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(length = 9, nullable = false)
    private String telephone;
    @ManyToOne(fetch = FetchType.EAGER)
    private Utilisateur utilisateur;
    @Column(length = 100, nullable = false)
    private String numeroPieceEmetteur;
    @Column(nullable = false)
    private String nomEmetteur;
    @Column(nullable = false)
    private String prenomEmetteur;
    @Column(length = 9, nullable = false)
    private String telephoneEmetteur;
    private int montant;
    private Date createdAt = new Date();

    public Transaction()
    {
    }

    public Transaction(String numeroPiece, String nom, String prenom, String telephone, Utilisateur utilisateur, String numeroPieceEmetteur, String nomEmetteur, String prenomEmetteur, String telephoneEmetteur, int montant, Date createdAt)
    {
        this.numeroPiece = numeroPiece;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.utilisateur = utilisateur;
        this.numeroPieceEmetteur = numeroPieceEmetteur;
        this.nomEmetteur = nomEmetteur;
        this.prenomEmetteur = prenomEmetteur;
        this.telephoneEmetteur = telephoneEmetteur;
        this.montant = montant;
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

    public String getNumeroPiece()
    {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece)
    {
        this.numeroPiece = numeroPiece;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public Utilisateur getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    public String getNumeroPieceEmetteur()
    {
        return numeroPieceEmetteur;
    }

    public void setNumeroPieceEmetteur(String numeroPieceEmetteur)
    {
        this.numeroPieceEmetteur = numeroPieceEmetteur;
    }

    public String getNomEmetteur()
    {
        return nomEmetteur;
    }

    public void setNomEmetteur(String nomEmetteur)
    {
        this.nomEmetteur = nomEmetteur;
    }

    public String getPrenomEmetteur()
    {
        return prenomEmetteur;
    }

    public void setPrenomEmetteur(String prenomEmetteur)
    {
        this.prenomEmetteur = prenomEmetteur;
    }

    public String getTelephoneEmetteur()
    {
        return telephoneEmetteur;
    }

    public void setTelephoneEmetteur(String telephoneEmetteur)
    {
        this.telephoneEmetteur = telephoneEmetteur;
    }

    public int getMontant()
    {
        return montant;
    }

    public void setMontant(int montant)
    {
        this.montant = montant;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }
}

