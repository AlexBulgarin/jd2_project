package by.sep.web.controller;

import by.sep.dto.CreateClientDto;
import by.sep.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @Autowired
    ClientService service;

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String getClientCreation(CreateClientDto dto) {
        try {
            service.createClient(dto);
        } catch (IllegalArgumentException e) {
            return "error-page";
        }
        return null;
    }
}
