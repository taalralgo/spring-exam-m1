package cours.uahb.controller;


import cours.uahb.model.Role;
import cours.uahb.model.Utilisateur;
import cours.uahb.repository.IRole;
import cours.uahb.repository.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String users(Model model)
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
}
