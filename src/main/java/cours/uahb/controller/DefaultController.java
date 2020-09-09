package cours.uahb.controller;

import cours.uahb.model.Utilisateur;
import cours.uahb.repository.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class DefaultController
{
    @Autowired
    private IUtilisateur utilisateurRepository;

    private Utilisateur connectedUser;

    @PreAuthorize("hasAuthority('ROLE_USER') OR hasAuthority('ROLE_ADMIN')")
    @GetMapping("/403")
    public String notFound()
    {
        return "403";
    }

    @PreAuthorize("hasAuthority('ROLE_CAISSIER') OR hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user/reset")
    public String resetpassword(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        connectedUser = utilisateurRepository.findByCode(user.getUsername());
        model.addAttribute("utilisateur", connectedUser);
        return "user/changepassword";
    }
}
