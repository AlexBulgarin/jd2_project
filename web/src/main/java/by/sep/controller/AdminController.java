package by.sep.controller;

import by.sep.dto.ClientDto;
import by.sep.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @Autowired
    ClientService service;

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/add-client")
    public String getAddClientPage() {
        return "add-client";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/admin/add-client")
    public ModelAndView getClientCreation(ClientDto dto) {
        ModelAndView modelAndView = new ModelAndView("admin");
        try {
            service.createClient(dto);
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.setViewName("error-page");
            return modelAndView;
        }
        return modelAndView;
    }
}
