package by.sep.controller;

import by.sep.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("client/transaction-{senderIban}")
    public ModelAndView getTransactionPage(@PathVariable("senderIban") String senderIban) {
        ModelAndView modelAndView = new ModelAndView("transaction");
        modelAndView.addObject("senderIban", senderIban);
        return modelAndView;
    }

    @PostMapping("client/transaction-{senderIban}")
    public String makeTransaction(@PathVariable("senderIban") String senderIban,
                                  @RequestParam("recipientCardNumber") String recipientCardNumber,
                                  @RequestParam("amount") Double amount) {
        transactionService.makeTransaction(senderIban, recipientCardNumber, amount);
        return "redirect:/transaction/success";
    }
}
