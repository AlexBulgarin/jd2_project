package by.sep.service;

import by.sep.dao.AccountDao;
import by.sep.dao.CardDao;
import by.sep.dao.TransactionDao;
import by.sep.pojo.Account;
import by.sep.pojo.Card;
import by.sep.pojo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionDao transactionDao;
    @Autowired
    AccountDao accountDao;
    @Autowired
    CardDao cardDao;

    @Override
    public void makeTransaction(String senderIban, String recipientCardNumber, Double amount) {
        Account senderAccount = accountDao.read(Account.class, senderIban);
        if (senderAccount == null) throw new IllegalArgumentException("Account not found.");
        Card recipientCard = cardDao.read(Card.class, recipientCardNumber);
        if (recipientCard == null) throw new IllegalArgumentException("Card not found.");
        Account recipientAccount = recipientCard.getAccount();
        if (senderAccount.equals(recipientAccount))
            throw new IllegalArgumentException("Sender and recipient accounts are equal.");
        if (!senderAccount.getCurrencyName().equals(recipientAccount.getCurrencyName()))
            throw new IllegalArgumentException("Sender account and recipient accounts currencies are not equal.");
        if (amount <= 0) throw new IllegalArgumentException("Transfer amount should be positive");
        if (senderAccount.getBalance() < amount)
            throw new IllegalArgumentException("You don't have enough money for transfer.");

        Transaction transaction = new Transaction();
        transaction.setSum(amount);
        transaction.setTransactionDateTime(LocalDateTime.now());
        transaction.setSenderAccount(senderAccount);
        transaction.setRecipientAccount(recipientAccount);

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);

        transactionDao.create(transaction);
        accountDao.update(senderAccount);
        accountDao.update(recipientAccount);
    }
}
