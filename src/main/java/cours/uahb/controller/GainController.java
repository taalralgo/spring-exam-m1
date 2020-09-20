package cours.uahb.controller;

import cours.uahb.model.Gain;
import cours.uahb.repository.GainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gains")
public class GainController
{
    @Autowired
    private GainRepository gainRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        List<Gain> gains = gainRepository.findAll();
        model.addAttribute("gains", gains);
        return "gain/index";
    }
}
