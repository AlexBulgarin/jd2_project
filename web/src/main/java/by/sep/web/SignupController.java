package by.sep.web;

import by.sep.dto.ClientDto;
import by.sep.service.CreateClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {
    @Autowired
    CreateClientService service;

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView getClientCreation(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        service.createClient(new ClientDto(null, firstName, lastName));
        return null;
    }
}
