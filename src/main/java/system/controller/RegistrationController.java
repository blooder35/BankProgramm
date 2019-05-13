package system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import system.exceptions.NotValidClientException;
import system.utility.LoggerUtility;
import system.utility.StringConstants;
import system.model.BankClient;
import system.service.ClientService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/newClient")
public final class RegistrationController {
    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/registrationForm", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm(@ModelAttribute("message") String message, Model model, HttpServletRequest request) {
        model.addAttribute("message", message);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newClient", new BankClient());
        modelAndView.setViewName("registrationForm");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("newClient") BankClient bankClient, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            int count = clientService.registerClient(bankClient);
            if (count > 0) {
                redirectAttributes.addFlashAttribute("message", "Client successfully registered");
            } else {
                redirectAttributes.addFlashAttribute("message", "Error while registering client: client already registered");
            }
        } catch (NotValidClientException e) {
            redirectAttributes.addFlashAttribute("message", "Input isn't valid try again");
        }
        return "redirect:/newClient/registrationForm";
    }
}
