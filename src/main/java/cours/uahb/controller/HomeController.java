package cours.uahb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/home")
public class HomeController
{
    @PreAuthorize("hasAuthority('ROLE_CAISSIER') OR hasAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public String index()
    {
        return "dashboard";
    }

    @PreAuthorize("hasAuthority('ROLE_CAISSIER') OR hasAuthority('ROLE_ADMIN')")
    @GetMapping("/home")
    public String home()
    {
        return "dashboard";
    }
}
