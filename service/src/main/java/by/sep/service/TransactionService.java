package by.sep.service;

public interface TransactionService {
    void makeTransaction (String senderIban, String recipientCardNumber, Double amount);
}
