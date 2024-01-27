package by.sep.service;

import by.sep.dao.AccountDao;
import by.sep.dao.CardDao;
import by.sep.dao.TransactionDao;
import by.sep.dto.TransactionDto;
import by.sep.pojo.Account;
import by.sep.pojo.Card;
import by.sep.pojo.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public void makeTransaction(String senderIban, String recipientCardNumber, double amount) {
        validateArguments(senderIban, recipientCardNumber, amount);
        Account senderAccount = accountDao.read(Account.class, senderIban);
        Card recipientCard = cardDao.read(Card.class, recipientCardNumber);
        Account recipientAccount = recipientCard.getAccount();

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

    @Override
    public List<TransactionDto> getTransactionsByIban(String iban, int page, int size) {
        if (iban == null) {
            throw new IllegalArgumentException("An argument iban cannot be null");
        }
        List<Transaction> transactions = transactionDao
                .readTransactionsByIban(iban, page * size, size);
        if (transactions == null) {
            throw new IllegalArgumentException("No transactions for iban:" + iban);
        }
        return transactions.stream()
                .map(transaction -> new TransactionDto(
                        transaction.getSum(),
                        transaction.getTransactionDateTime(),
                        transaction.getSenderAccount().getIban(),
                        transaction.getRecipientAccount().getIban()))
                .toList();
    }

    private void validateArguments(String senderIban, String recipientCardNumber, double amount) {
        if (senderIban == null) throw new IllegalArgumentException("An argument senderIban cannot be null");
        if (recipientCardNumber == null)
            throw new IllegalArgumentException("An argument recipientCardNumber cannot be null");
        if (amount == 0) throw new IllegalArgumentException("An argument amount cannot be 0");
        Account senderAccount = accountDao.read(Account.class, senderIban);
        Card recipientCard = cardDao.read(Card.class, recipientCardNumber);
        Account recipientAccount = recipientCard.getAccount();
        if (senderAccount.equals(recipientAccount))
            throw new IllegalArgumentException("Sender and recipient accounts are equal.");
        if (!senderAccount.getCurrencyName().equals(recipientAccount.getCurrencyName()))
            throw new IllegalArgumentException("Sender account and recipient accounts currencies are not equal.");
        if (amount <= 0) throw new IllegalArgumentException("Transfer amount should be positive");
        if (senderAccount.getBalance() < amount)
            throw new IllegalArgumentException("You don't have enough money for transfer.");
    }
}
