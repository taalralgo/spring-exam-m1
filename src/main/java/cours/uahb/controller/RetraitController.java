package cours.uahb.controller;

import cours.uahb.config.Utils;
import cours.uahb.model.Retrait;
import cours.uahb.model.Utilisateur;
import cours.uahb.repository.IUtilisateur;
import cours.uahb.repository.RetraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/retraits")
public class RetraitController
{
    @Autowired
    private RetraitRepository retraitRepository;


    @Autowired
    private IUtilisateur utilisateurRepository;

    @Autowired
    private Utils utils;

    @GetMapping("/")
    public String index(Model model)
    {
        Utilisateur caissier = utilisateurRepository.findByCode(utils.getConnectedUser());
        model.addAttribute("retraits", retraitRepository.findAllByCaissier(caissier));
        return "retrait/index";
    }
    @GetMapping("/print/{id}")
    public String index(@PathVariable String id, Model model)
    {
        try
        {
            Long retraitId = Long.parseLong(id);
            Retrait retrait = retraitRepository.findById(retraitId).get();
            double taux = (double) 5/100;
            double gain = retrait.getTransaction().getMontant() * taux;
            //Montant a retirer
            double montant = retrait.getTransaction().getMontant() - gain;
            model.addAttribute("retrait", retraitRepository.findById(retraitId).get());
            model.addAttribute("montant", montant);
            model.addAttribute("gain", gain);
            return "retrait/print";
        }
        catch (Exception e)
        {
            return "redirect:/retraits/";
        }

    }
}
