package by.sep.web.controller;

import by.sep.dto.CreateClientDto;
import by.sep.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {
    @Autowired
    ClientService service;

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView getClientCreation(CreateClientDto dto) {
        service.createClient(dto);
        return null;
    }
}
