package by.sep.controller.rest;

import by.sep.dto.TransactionDto;
import by.sep.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionRestController {
    @Autowired
    TransactionService transactionService;

    @GetMapping("client/api/transactions/{iban}")
    @Secured("ROLE_USER")
    public List<TransactionDto> getTransactionsByIban(
            @PathVariable("iban") String iban,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size) {
        return transactionService.getTransactionsByIban(iban, page, size);
    }
}
