package cours.uahb.controller;

import cours.uahb.config.Utils;
import cours.uahb.model.*;
import cours.uahb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/operations")
//@PreAuthorize("hasAuthority('ROLE_CAISSIER')")
public class OperationController
{
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private IUtilisateur utilisateurRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RetraitRepository retraitRepository;

    @Autowired
    private GainRepository gainRepository;

    @Autowired
    private Utils utils;

    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("operations", operationRepository.findAllByRetirerEquals(false));
        return "operation/index";
    }
    @GetMapping("/retirer/{operationId}")
    public ResponseEntity retirer(@PathVariable String operationId)
    {
        try
        {
            Optional<Operation> operation = operationRepository.findById(Long.parseLong(operationId));
            if (operation.isPresent())
            {
                Operation op = operation.get();
                op.setRetirer(true);
                op.setDateRetrait(new Date());
                Utilisateur caissier = utilisateurRepository.findByCode(utils.getConnectedUser());
                Optional<Transaction> trans = transactionRepository.findById(op.getTransaction().getId());
                if (trans.isPresent())
                {
                    Transaction transaction = trans.get();
//                    if(caissier.getIv() >= transaction.getMontant())
//                    {
                        Retrait retrait = new Retrait(caissier,transaction, new Date());
                        op.setDateRetrait(new Date());
                        caissier.setIv(caissier.getIv() + transaction.getMontant());
                        operationRepository.save(op);
                        retraitRepository.save(retrait);
                        utilisateurRepository.save(caissier);

                        double taux = (double) 5/100;
                        double gain = transaction.getMontant() * taux;
                        //Montant a retirer
                        double montant = transaction.getMontant() - gain;
                        //CALCULE POUR LE SYSTEME
                        double systeme = (double)40/100 * gain;
                        double etat = (double)20/100 * gain;
                        double caissier1 = (double)20/100 * gain;
                        double caissier2 = (double)20/100 * gain;

                        Gain gainSave = new Gain();
                        gainSave.setCaissier1(transaction.getUtilisateur());
                        gainSave.setCaissier2(caissier);
                        gainSave.setCaissier2(caissier);
                        gainSave.setSysteme(systeme);
                        gainSave.setGainCaissier1(caissier1);
                        gainSave.setGainCaissier2(caissier2);
                        gainSave.setEtat(etat);
                        gainSave.setCreatedAt(new Date());
                        gainRepository.save(gainSave);

                        return ResponseEntity.ok(new Response("success"));
//                    }
//                    return ResponseEntity.ok(new Response("iv"));
                }
                return ResponseEntity.ok(new Response("error"));
            }
            return ResponseEntity.ok(new Response("error"));
        }
        catch (Exception e)
        {
            return ResponseEntity.ok(new Response("error"));
        }
    }
}
