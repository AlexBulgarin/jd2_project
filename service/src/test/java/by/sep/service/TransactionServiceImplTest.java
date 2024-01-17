package by.sep.service;

import by.sep.TestServiceConfiguration;
import by.sep.dao.AccountDao;
import by.sep.dao.CardDao;
import by.sep.dao.TransactionDao;
import by.sep.pojo.Account;
import by.sep.pojo.Card;
import by.sep.pojo.CurrencyName;
import by.sep.pojo.Transaction;
import org.iban4j.Iban;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
public class TransactionServiceImplTest {
    @InjectMocks
    TransactionServiceImpl service;
    @Mock
    TransactionDao transactionDao;
    @Mock
    AccountDao accountDao;
    @Mock
    CardDao cardDao;
    Account senderAccount;
    String senderIban = Iban.random().toString();
    double senderBalance = 333.33;
    Account recipientAccount;
    String recipientIban = Iban.random().toString();
    double recipientBalance = 555.55;
    Card card;
    String testNumber = "Test Number";

    @Before
    public void setUp() throws Exception {
        senderAccount = new Account(senderIban, senderBalance, CurrencyName.BYN, LocalDate.now());
        recipientAccount = new Account(recipientIban, recipientBalance, CurrencyName.BYN, LocalDate.now());
        card = new Card(testNumber, LocalDate.now().plusMonths(22), "364");
        card.setAccount(recipientAccount);

        when(accountDao.read(Account.class, senderIban)).thenReturn(senderAccount);
        when(cardDao.read(Card.class, testNumber)).thenReturn(card);
    }

    @Test
    public void makeTransaction() {
        double transferSum = 100;
        service.makeTransaction(senderIban, testNumber, transferSum);
        verify(transactionDao, times(1)).create(any(Transaction.class));
        verify(accountDao, times(1)).update(senderAccount);
        verify(accountDao, times(1)).update(recipientAccount);
        assertEquals(senderBalance - transferSum, senderAccount.getBalance(), 0);
        assertEquals(recipientBalance + transferSum, recipientAccount.getBalance(), 0);
    }
}