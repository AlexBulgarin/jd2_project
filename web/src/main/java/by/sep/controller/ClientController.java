package by.sep.controller;

import by.sep.dto.AccountDto;
import by.sep.dto.ClientLoginDto;
import by.sep.security.AuthenticationService;
import by.sep.service.AccountService;
import by.sep.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    AccountService accountService;

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String getLoginCreation(ClientLoginDto dto) {
        try {
            clientService.createClientLogin(dto);
        } catch (IllegalArgumentException e) {
            return "error-page";
        }
        return "index";
    }

    @Secured("ROLE_USER")
    @GetMapping("/client")
    public ModelAndView getUserPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("client");
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            String clientId = authenticationService.getIdByUsername(username);
            List<AccountDto> accountDtos = accountService.readAccountsByClientId(clientId);
            modelAndView.addObject("accountDtos", accountDtos);
        }
        return modelAndView;
    }

}