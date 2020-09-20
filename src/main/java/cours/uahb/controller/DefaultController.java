package cours.uahb.controller;

import cours.uahb.model.Utilisateur;
import cours.uahb.repository.IUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DefaultController
{
    @Autowired
    private IUtilisateur utilisateurRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Utilisateur connectedUser;

    @GetMapping("/403")
    public String notFound()
    {
        return "403";
    }

    @GetMapping("/error")
    public String error(Model model, Error error)
    {
        model.addAttribute("error", error.getMessage());
        return "error";
    }

    @PreAuthorize("hasAuthority('ROLE_CAISSIER') OR hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user/reset")
    public String resetpassword(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur user = (Utilisateur) auth.getPrincipal();
        connectedUser = utilisateurRepository.findByCode(user.getCode());
        model.addAttribute("utilisateur", connectedUser);
        return "user/changepassword";
    }

    @PostMapping("/user/reset")
    public String reset(@RequestParam(name = "oldpassword") String oldpassword,
                                @RequestParam(name = "password") String password,
                                @RequestParam(name = "confirmation") String confirmation,
                                Model model)
    {
        String error = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur user = (Utilisateur) auth.getPrincipal();
        connectedUser = utilisateurRepository.findByCode(user.getCode());
        if(password.length() < 6) {
            error+= "Le mot de passe doit faire au moins 6 caracteres.";
            model.addAttribute("erreur",error);
            model.addAttribute("utilisateur", connectedUser);
            return "user/changepassword";
        }
        if(password.equals(oldpassword)) {
            error+= "L'ancien mot de passe et le nouveau mot de passe doit etre different.";
            model.addAttribute("erreur",error);
            model.addAttribute("utilisateur", connectedUser);
            return "user/changepassword";
        }
        if(!password.equals(confirmation)) {
            error+= "La confirmation ne correspond pas au mot de passe.";
            model.addAttribute("erreur",error);
            model.addAttribute("utilisateur", connectedUser);
            return "user/changepassword";
        }

        connectedUser.setPwd(bCryptPasswordEncoder.encode(password));
        connectedUser.setChanged(true);
        utilisateurRepository.save(connectedUser);
        return "redirect:/home";

    }
}
