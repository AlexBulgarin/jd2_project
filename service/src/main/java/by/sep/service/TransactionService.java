package by.sep.service;

import by.sep.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    void makeTransaction (String senderIban, String recipientCardNumber, double amount);
    List<TransactionDto> getTransactionsByIban(String iban, int page, int size);
}
