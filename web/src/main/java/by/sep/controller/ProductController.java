package by.sep.controller;

import by.sep.dto.DepositDto;
import by.sep.dto.LoanDto;
import by.sep.dto.OpenProductDto;
import by.sep.dto.ProductDto;
import by.sep.security.AuthenticationService;
import by.sep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService service;
    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/products")
    public ModelAndView getProducts() {
        List<ProductDto> products = service.readProducts(ProductDto.class);
        List<LoanDto> loans = service.readProducts(LoanDto.class);
        List<DepositDto> deposits = service.readProducts(DepositDto.class);
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("products", products);
        modelAndView.addObject("loans", loans);
        modelAndView.addObject("deposits", deposits);
        return modelAndView;
    }

    @GetMapping("products/open-{id}")
    public ModelAndView getProductOpeningPage(@PathVariable("id") String id) {
        ProductDto productDto = service.readById(id);
        ModelAndView modelAndView = new ModelAndView("open-product");
        modelAndView.addObject(productDto);
        return modelAndView;
    }

    @PostMapping("products/open-{id}")
    public String getProductOpening(Authentication authentication, @PathVariable("id") String productId,
                                    OpenProductDto dto) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            String clientId = authenticationService.getIdByUsername(username);
            service.addProductToClient(clientId, productId, dto);
        }
        return "client";
    }
}
