package cours.uahb.controller;

import cours.uahb.config.Utils;
import cours.uahb.model.Operation;
import cours.uahb.model.Transaction;
import cours.uahb.model.Utilisateur;
import cours.uahb.repository.IUtilisateur;
import cours.uahb.repository.OperationRepository;
import cours.uahb.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
@RequestMapping("/transactions")
@PreAuthorize("hasAuthority('ROLE_CAISSIER')")
public class TransactionController
{
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private IUtilisateur utilisateurRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private OperationRepository operationRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        model.addAttribute("transactions", transactionRepository.findAll());
        return "transaction/index";
    }

    @GetMapping("/create")
    public String create(Model model)
    {
        Transaction transaction = new Transaction();
        transaction.setUtilisateur(new Utilisateur());
        model.addAttribute("transaction", transaction);
        return "transaction/create";
    }

    @PostMapping("/add")
    public String save(Model model, @ModelAttribute("transaction") Transaction transaction)
    {
        String error = "";
        transaction.setCode(randomString(10));
        if(transaction.getMontant() < 5)
        {
            error = "Le montant de la transaction n'est pas correct";
            model.addAttribute("error", error);
            return "transaction/create";
        }
        if(transaction.getCode().length() < 10)
        {
            error = "Le code n'est pas valide";
            model.addAttribute("error", error);
            return "transaction/create";
        }
        Utilisateur user = utilisateurRepository.findByCode(utils.getConnectedUser());
        transaction.setUtilisateur(user);
        transactionRepository.save(transaction);
        Operation operation = new Operation(transaction, "Envoie", false, new Date());
        operationRepository.save(operation);

        model.addAttribute("success", "L'operation a été effectuée avec succès!");
        return "redirect:/transactions/";
    }

    public String randomString(int n){

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString().toLowerCase();
    }
}
