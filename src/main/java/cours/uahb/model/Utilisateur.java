

package cours.uahb.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Utilisateur implements UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String numeroPiece;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String prenom;
    @Column(length = 9, nullable = false, unique = true)
    private String telephone;
//    Si caissier ou admin
    private String code;
    private String photo;
    @Column(length = 40)
    private String numeroContrat;
    @Column(columnDefinition = "text")
    private String articleContrat;
    @Column(nullable = false)
    private String pwd;
    private boolean changed = false;
    @Transient
    private MultipartFile[] parts;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;


    // Upload files.
    @Transient
    private MultipartFile[] fileDatas;

    public MultipartFile[] getFileDatas() {
        return fileDatas;
    }

    public void setFileDatas(MultipartFile[] fileDatas) {
        this.fileDatas = fileDatas;
    }

    public Utilisateur() {
    }


    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getNumeroContrat()
    {
        return numeroContrat;
    }

    public void setNumeroContrat(String numeroContrat)
    {
        this.numeroContrat = numeroContrat;
    }

    public String getArticleContrat()
    {
        return articleContrat;
    }

    public void setArticleContrat(String articleContrat)
    {
        this.articleContrat = articleContrat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public MultipartFile[] getParts()
    {
        return parts;
    }

    public void setParts(MultipartFile[] parts)
    {
        this.parts = parts;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List authorities = new ArrayList();

        authorities.add(new SimpleGrantedAuthority(role.getLibRole()));

        return authorities;
    }

    @Override
    public String getPassword()
    {
        return pwd;
    }

    @Override
    public String getUsername()
    {
        return code;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}