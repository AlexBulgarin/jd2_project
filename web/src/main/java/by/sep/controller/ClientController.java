package by.sep.controller;

import by.sep.dto.ClientLoginDto;
import by.sep.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {
    @Autowired
    ClientService service;

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String getLoginCreation(ClientLoginDto dto) {
        try {
            service.createClientLogin(dto);
        } catch (IllegalArgumentException e) {
            return "error-page";
        }
        return "index";
    }

    @Secured("ROLE_USER")
    @GetMapping("/client")
    public String getUserPage() {
        return "client";
    }
}
