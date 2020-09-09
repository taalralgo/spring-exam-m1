package cours.uahb.controller;


import cours.uahb.model.Response;
import cours.uahb.model.Role;
import cours.uahb.model.Utilisateur;
import cours.uahb.repository.IRole;
import cours.uahb.repository.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/admin/")
public class AdminController
{
    @Autowired
    private IUtilisateur utilisateurRepository;
    @Autowired
    private IRole roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Utilisateur connectedUser;

    @GetMapping("/utilisateurs")
    public String index(Model model)
    {
        Utilisateur u = new Utilisateur();
        u.setRole(new Role());
        String uploadsUrl = "file:///D:\\uploads\\";
        model.addAttribute("utilisateurs", utilisateurRepository.findAll());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("utilisateur", u);
        model.addAttribute("uploads", uploadsUrl);
        return "user/index";
    }

    @PostMapping("/user/add")
    public String save(@ModelAttribute("utilisateur") Utilisateur utilisateur) throws Exception
    {
        byte[] bytes = null;
        Path path = null;

        if(utilisateur.getParts()[0].getName().equals(""))
        {
            utilisateur.setPhoto("noimg.png");
        }
        else
        {
            MultipartFile part = utilisateur.getParts()[0];
            bytes = part.getBytes();
            path = Paths.get("D://uploads//" + part.getOriginalFilename() );
            utilisateur.setPhoto(part.getOriginalFilename());
        }
//        utilisateur.setPwd(bCryptPasswordEncoder.encode("passer1234"));
        utilisateur.setPwd(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
        if(utilisateur.getRole().getId().equals(1))
        {
            utilisateur.setChanged(true);
            utilisateur.setLogin(utilisateur.getLogin());
            utilisateur.setCode(utilisateur.getLogin());
            utilisateur.setPhoto("noimg.png");
        }
        if(utilisateur.getRole().getId().equals(2))
        {
            utilisateur.setLogin(utilisateur.getCode());
        }
        utilisateurRepository.save(utilisateur);
        if(bytes.length != 0)
        {
            Files.write(path, bytes);
        }
        return "redirect:/admin/utilisateurs";
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
