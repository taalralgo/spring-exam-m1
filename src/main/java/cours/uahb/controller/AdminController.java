package cours.uahb.controller;


import com.fasterxml.jackson.annotation.JsonIgnore;
import cours.uahb.config.Utils;
import cours.uahb.model.Response;
import cours.uahb.model.Role;
import cours.uahb.model.Utilisateur;
import cours.uahb.repository.IRole;
import cours.uahb.repository.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.rmi.CORBA.Util;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/admin/")
//@PreAuthorize("hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_SUPER')")
public class AdminController
{
    @Autowired
    private IUtilisateur utilisateurRepository;
    @Autowired
    private IRole roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Utils utils;

    private Utilisateur connectedUser;

    @GetMapping("/utilisateurs")
    public String index(Model model)
    {
        Utilisateur u = new Utilisateur();
        u.setRole(new Role());
        String uploadsUrl = "file:///D:\\uploads\\";
        Utilisateur user = utilisateurRepository.findByCode(utils.getConnectedUser());
        if(user.getRole().getLibRole().equals("ROLE_ADMIN"))
        {
            model.addAttribute("utilisateurs", utilisateurRepository.findAllByAdminId(user.getId()));
        }
        else
        {
            model.addAttribute("utilisateurs", utilisateurRepository.findAll());
        }
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("utilisateur", u);
        model.addAttribute("auth", utilisateurRepository.findByCode(utils.getConnectedUser()));
        model.addAttribute("uploads", uploadsUrl);
        return "user/index";
    }

    @PostMapping("/user/add")
    public String save(@ModelAttribute("utilisateur") Utilisateur utilisateur) throws Exception
    {
        byte[] bytes = null;
        Path path = null;

        if (utilisateur.getParts()[0].getName().equals(""))
        {
            utilisateur.setPhoto("noimg.png");
        }
        else
        {
            MultipartFile part = utilisateur.getParts()[0];
            bytes = part.getBytes();
            path = Paths.get("D://uploads//" + part.getOriginalFilename());
            utilisateur.setPhoto(part.getOriginalFilename());
        }
//        utilisateur.setPwd(bCryptPasswordEncoder.encode("passer1234"));
        utilisateur.setPwd(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
        if (utilisateur.getRole().getId().equals(1) || utilisateur.getRole().getId().equals(4)) //ROLE_ADMIN OU ROLE_SUPER
        {
            utilisateur.setChanged(true);
            utilisateur.setLogin(utilisateur.getLogin());
            utilisateur.setCode(utilisateur.getLogin());
            utilisateur.setPhoto("noimg.png");
        }
        if (utilisateur.getRole().getId().equals(2)) // ROLE_CAISSIER
        {
            if (utilisateur.getCode().equals(""))
            {
                String codeGenerate = utilisateur.getPrenom().toLowerCase().substring(0, 2)
                        + '-'
                        + utilisateur.getNom().toLowerCase().substring(0, 2)
                        + '-'
                        + utilisateur.getTelephone().toLowerCase().substring(0, 2)
                        + '-'
                        + utilisateur.getNumeroPiece().toLowerCase().substring(0, 4);
                utilisateur.setCode(codeGenerate);
            }
            utilisateur.setLogin(utilisateur.getCode());
            utilisateur.setAdminId(utilisateurRepository.findByCode(utils.getConnectedUser()).getId());
        }
        utilisateurRepository.save(utilisateur);
        if (bytes.length != 0)
        {
            Files.write(path, bytes);
        }
        return "redirect:/admin/utilisateurs";
    }


    @GetMapping("user/edit/{id}")
    public ResponseEntity edit(@PathVariable String id)
    {
        try
        {
            Optional<Utilisateur> utilisateur = utilisateurRepository.findById(Integer.parseInt(id));
            if (utilisateur.isPresent())
            {
                return ResponseEntity.ok(utilisateur.get());
            }
            return ResponseEntity.ok(new Response("error"));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok(new Response("error"));
        }
    }

    @GetMapping("/user/delete/{id}")
    public ResponseEntity delete(Model model, @PathVariable String id)
    {
        try
        {
            Optional<Utilisateur> utilisateur = utilisateurRepository.findById(Integer.parseInt(id));
            if (utilisateur.isPresent())
            {
                utilisateurRepository.delete(utilisateur.get());
                return ResponseEntity.ok(new Response("success"));
            }
            return ResponseEntity.ok(new Response("error"));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok(new Response("error"));
        }
    }
}
