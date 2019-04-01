package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import system.model.BankTransaction;
import system.service.TransactionService;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/listClient", method = RequestMethod.POST)
    public String listClientTransactions(@ModelAttribute("clientID") int clientID, Model model) {
        model.addAttribute("transactionSenderList", transactionService.getUserSenderTransactions(clientID));
        model.addAttribute("transactionRecipientList", transactionService.getUserRecipientTransactions(clientID));
        return "listTransactions";
    }

    @RequestMapping(value = "/listAccount", method = RequestMethod.POST)
    public String listAccountTransactions(@ModelAttribute("accountID") int accountID, Model model) {
        model.addAttribute("transactionSenderList", transactionService.getAccountSenderTransactions(accountID));
        model.addAttribute("transactionRecipientList", transactionService.getAccountRecipientTransactions(accountID));
        return "listTransactions";
    }

    @RequestMapping(value = "/newTransaction", method = RequestMethod.GET)
    public ModelAndView newTransactionPage(@ModelAttribute("recipient") String recipient, @ModelAttribute("sender") String sender, Model model) {
        model.addAttribute("recipient", recipient);
        model.addAttribute("sender", sender);
        ModelAndView modelAndView = new ModelAndView();
        BankTransaction bankTransaction = new BankTransaction();
        if (sender.length() > 0) {
            bankTransaction.setSender(Integer.parseInt(sender));
        }
        if (recipient.length() > 0) {
            bankTransaction.setRecipient(Integer.parseInt(recipient));
        }
        modelAndView.addObject("newTransaction", bankTransaction);
        modelAndView.setViewName("newTransaction");
        return modelAndView;
    }

    @RequestMapping(value = "/addNewTransaction", method = RequestMethod.POST)
    public @ResponseBody
    String addTransaction(@ModelAttribute("newTransaction") BankTransaction bankTransaction, RedirectAttributes redirectAttributes, Model model) {
        String responce = transactionService.newTransaction(bankTransaction);
        redirectAttributes.addFlashAttribute("message", responce);
        return responce;
    }

}
