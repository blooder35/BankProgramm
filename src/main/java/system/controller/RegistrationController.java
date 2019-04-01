package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import system.model.BankClient;
import system.service.ClientService;

@Controller
@RequestMapping("/newClient")
public class RegistrationController {
    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/registrationForm", method = RequestMethod.GET)
    public ModelAndView getRegistrationForm(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("message", message);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("newClient", new BankClient());
        modelAndView.setViewName("registrationForm");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("newClient") BankClient bankClient, RedirectAttributes redirectAttributes) {
        String responce = clientService.registerClient(bankClient);
        redirectAttributes.addFlashAttribute("message", responce);
        return "redirect:/newClient/registrationForm";
    }
}
