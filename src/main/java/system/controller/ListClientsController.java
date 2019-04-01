package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import system.model.BankClient;
import system.service.ClientService;

@Controller
@RequestMapping("/list")
public class ListClientsController {
    @Autowired
    ClientService clientService;

    @RequestMapping("/clients")
    public String listClients(Model model, @ModelAttribute("message") String message) {
        model.addAttribute("clientsList", clientService.listClients());
        model.addAttribute("message", message);
        return "listClients";
    }

    @RequestMapping(value = "/alikeClients", method = RequestMethod.GET)
    public String listAlikeClients(@ModelAttribute("clientData") BankClient bankClient, Model model) {
        model.addAttribute("clientsList", clientService.getAlikeClients(bankClient));
        return "listClients";
    }

    @RequestMapping(value = "/alikeClientsForm", method = RequestMethod.GET)
    public String alikeClientsForm(Model model) {
        model.addAttribute("clientData", new BankClient());
        return "alikeClientsForm";
    }
}
