package cours.uahb.controller;

import cours.uahb.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/home")
public class HomeController
{
    @Autowired
    private OperationRepository operationRepository;

    @PreAuthorize("hasAuthority('ROLE_CAISSIER') OR hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_SUPER')")
    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("retirer", operationRepository.countAllByRetirerEquals(true));
        model.addAttribute("nonretirer", operationRepository.countAllByRetirerEquals(false));
        return "dashboard";
    }

    @PreAuthorize("hasAuthority('ROLE_CAISSIER') OR hasAuthority('ROLE_ADMIN') OR hasAuthority('ROLE_SUPER')")
    @GetMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("retirer", operationRepository.countAllByRetirerEquals(true));
        model.addAttribute("nonretirer", operationRepository.countAllByRetirerEquals(false));
        return "dashboard";
    }
}
