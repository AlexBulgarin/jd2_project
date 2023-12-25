package by.sep.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/index.jsp"})
    public String getHomePage() {
        return "index";
    }
}
