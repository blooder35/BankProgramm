package system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import system.utility.LoggerUtility;
import system.utility.StringConstants;
import system.model.BankClient;
import system.service.AccountService;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/accounts")
public final class ListAccountsController {
    @Autowired
    AccountService accountService;
    private final Logger logger=LoggerFactory.getLogger(StringConstants.USER_LOGGER);

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public String listAccounts(@ModelAttribute("clientID") int clientID, Model model, @ModelAttribute("message") String message, @ModelAttribute("client") BankClient bankClient, HttpServletRequest request) {
        LoggerUtility.logRequestString(request,logger);
        model.addAttribute("accountsList", accountService.listUserAccounts(clientID));
        model.addAttribute("message", message);
        return "listAccounts";
    }

    @RequestMapping(value = "/newAccount", method = RequestMethod.POST)
    public String registerAccount(@ModelAttribute("clientID") int ownerID, RedirectAttributes redirectAttributes,HttpServletRequest request) {
        LoggerUtility.logRequestString(request,logger);
        int count=accountService.registerAccount(ownerID);
        if (count > 0) {
            redirectAttributes.addFlashAttribute("message", "accountCreated for:" + ownerID);
        } else {
            redirectAttributes.addFlashAttribute("message", "account wasn't created" + ownerID);
        }
        return "redirect:/list/clients";
    }

    @RequestMapping(value = "/closeAccount", method = RequestMethod.POST)
    public String closeAccount(@ModelAttribute("accountID") int accountID, @ModelAttribute("ownerID") int ownerID, RedirectAttributes redirectAttributes,HttpServletRequest request) {
        LoggerUtility.logRequestString(request,logger);
        int count=accountService.closeAccount(accountID);
        if (count > 0) {
            redirectAttributes.addFlashAttribute("message", "accountClosed:" + accountID);
        } else {
            redirectAttributes.addFlashAttribute("message","couldn't close account:"+accountID);
        }
        redirectAttributes.addAttribute("clientID", ownerID);
        return "redirect:/accounts/";
    }


}
