package by.sep.controller;

import by.sep.dto.DepositDto;
import by.sep.dto.LoanDto;
import by.sep.dto.OpenProductDto;
import by.sep.dto.ProductDto;
import by.sep.security.AuthenticationService;
import by.sep.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    ProductService productService;
    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/products")
    public ModelAndView getProducts() {
        List<ProductDto> products = productService.readProducts(ProductDto.class);
        List<LoanDto> loans = productService.readProducts(LoanDto.class);
        List<DepositDto> deposits = productService.readProducts(DepositDto.class);
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("products", products);
        modelAndView.addObject("loans", loans);
        modelAndView.addObject("deposits", deposits);
        return modelAndView;
    }

    @Secured("ROLE_USER")
    @GetMapping("products/open/{productType}/{id}/{sum}")
    public String getProductOpeningPage(@PathVariable("productType") String productType,
                                        @PathVariable("id") String productId,
                                        @PathVariable("sum") Double sum) {
        return "open-product";
    }

    @Secured("ROLE_USER")
    @PostMapping("products/open/{productType}/{id}/{sum}")
    public ModelAndView openProduct(Authentication authentication,
                                    @PathVariable("productType") String productType,
                                    @PathVariable("id") String productId,
                                    @PathVariable("sum") Double sum,
                                    OpenProductDto dto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/client");
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            String clientId = authenticationService.getIdByUsername(username);
            productService.addProductToClient(clientId, productId, dto);
            modelAndView.addObject("productType", productType);
            modelAndView.addObject("sum", sum);
        }
        return modelAndView;
    }

    @Secured("ROLE_USER")
    @GetMapping("client/add-card-{iban}")
    public String issueAdditionalCard(@PathVariable("iban") String iban) {
        productService.addNewCardToExistingAccount(iban);
        return "redirect:/client";
    }
}
