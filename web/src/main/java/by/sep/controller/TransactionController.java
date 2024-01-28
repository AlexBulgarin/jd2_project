package by.sep.controller;

import by.sep.dto.TransactionDto;
import by.sep.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Secured("ROLE_USER")
    @GetMapping("client/transaction-{senderIban}")
    public ModelAndView getTransactionPage(@PathVariable("senderIban") String senderIban) {
        ModelAndView modelAndView = new ModelAndView("transaction");
        modelAndView.addObject("senderIban", senderIban);
        return modelAndView;
    }

    @Secured("ROLE_USER")
    @PostMapping("client/transaction-{senderIban}")
    public ModelAndView makeTransaction(@PathVariable("senderIban") String senderIban,
                                        @RequestParam("recipientCardNumber") String recipientCardNumber,
                                        @RequestParam("amount") double amount) {
        ModelAndView modelAndView = new ModelAndView("redirect:/client");
        try {
            transactionService.makeTransaction(senderIban, recipientCardNumber, amount);
        } catch (Exception e) {
            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.setViewName("error-page");
            return modelAndView;
        }
        return modelAndView;
    }

    @GetMapping("client/transactions/{iban}")
    public ModelAndView getTransactionsByIban(
            @PathVariable(name = "iban") String iban,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "2") Integer size) {
        List<TransactionDto> transactions = transactionService.getTransactionsByIban(iban, page, size);
        List<TransactionDto> allTransactions = transactionService.getTransactionsByIban(iban, 0, Integer.MAX_VALUE);
        int totalPages = (int) Math.ceil((double) allTransactions.size() / size);
        ModelAndView modelAndView = new ModelAndView("transaction-list");
        modelAndView.addObject("transactions", transactions);
        modelAndView.addObject("page", page);
        modelAndView.addObject("size", size);
        modelAndView.addObject("iban", iban);
        modelAndView.addObject("totalPages", totalPages);
        return modelAndView;
    }

}
