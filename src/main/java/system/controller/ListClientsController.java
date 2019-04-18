package system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import system.utility.LoggerUtility;
import system.utility.StringConstants;
import system.model.BankClient;
import system.service.ClientService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/list")
public final class ListClientsController {
    @Autowired
    ClientService clientService;
    private final Logger logger= LoggerFactory.getLogger(StringConstants.USER_LOGGER);
    @RequestMapping("/clients")
    public String listClients(Model model, @ModelAttribute("message") String message, HttpServletRequest request) {
        LoggerUtility.logRequestString(request,logger);
        model.addAttribute("clientsList", clientService.listClients());
        model.addAttribute("message", message);
        return "listClients";
    }

    @RequestMapping(value = "/alikeClients", method = RequestMethod.GET)
    public String listAlikeClients(@ModelAttribute("clientData") BankClient bankClient, Model model, HttpServletRequest request) {
        LoggerUtility.logRequestString(request,logger);
        model.addAttribute("clientsList", clientService.getAlikeClients(bankClient));
        return "listClients";
    }

    @RequestMapping(value = "/alikeClientsForm", method = RequestMethod.GET)
    public String alikeClientsForm(Model model,HttpServletRequest request) {
        LoggerUtility.logRequestString(request,logger);
        model.addAttribute("clientData", new BankClient());
        return "alikeClientsForm";
    }
}
