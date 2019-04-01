package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import system.model.BankClient;
import system.service.AccountService;


@Controller
@RequestMapping("/accounts")
public class ListAccountsController {
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String listAccounts(@ModelAttribute("clientID") int clientID, Model model, @ModelAttribute("message") String message, @ModelAttribute("client") BankClient bankClient) {
        model.addAttribute("accountsList", accountService.listUserAccounts(clientID));
        model.addAttribute("message", message);
        return "listAccounts";
    }

    @RequestMapping(value = "/newAccount", method = RequestMethod.POST)
    public String registerAccount(@ModelAttribute("clientID") int ownerID, RedirectAttributes redirectAttributes) {
        accountService.registerAccount(ownerID);
        redirectAttributes.addFlashAttribute("message", "accountCreated for:" + ownerID);
        return "redirect:/list/clients";
    }

    @RequestMapping(value = "/closeAccount", method = RequestMethod.POST)
    public String closeAccount(@ModelAttribute("accountID") int accountID, @ModelAttribute("ownerID") int ownerID, RedirectAttributes redirectAttributes) {
        String responce = accountService.closeAccount(accountID);
        redirectAttributes.addAttribute("clientID", ownerID);
        redirectAttributes.addFlashAttribute("message", "accountClosed:" + accountID);
        return "redirect:/accounts/";
    }


}
