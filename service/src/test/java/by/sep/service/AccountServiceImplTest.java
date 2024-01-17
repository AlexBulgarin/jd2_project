package by.sep.service;

import by.sep.TestServiceConfiguration;
import by.sep.dao.AccountDao;
import by.sep.dao.CardDao;
import by.sep.dto.AccountDto;
import by.sep.pojo.Account;
import by.sep.pojo.Card;
import by.sep.pojo.CurrencyName;
import by.sep.pojo.product.Loan;
import by.sep.pojo.product.Product;
import org.iban4j.Iban;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
public class AccountServiceImplTest {
    @InjectMocks
    AccountServiceImpl service;
    @Mock
    AccountDao accountDao;
    @Mock
    CardDao cardDao;
    String clientId = "Test Id";

    @Before
    public void setUp() {
        Account account1 = new Account(Iban.random().toString(),
                100.0, CurrencyName.USD, LocalDate.now());
        Account account2 = new Account(Iban.random().toString(),
                200.0, CurrencyName.EUR, LocalDate.now());
        account1.setProduct(new Product("test Id", "test Name",
                "test Description", 1));
        account2.setProduct(new Loan("test Id", "test Name",
                "test Description", 1, 1.1, 200.50));
        List<Account> mockAccounts = List.of(account1, account2);
        when(accountDao.readAccountsByClientId(clientId)).thenReturn(mockAccounts);
        List<Card> mockCards = List.of(
                new Card("test number", LocalDate.now().plusMonths(1), "305"),
                new Card("test number 2", LocalDate.now().plusMonths(2), "940"));
        when(cardDao.readCardsByIban(mockAccounts.get(0).getIban())).thenReturn(mockCards);
    }

    @Test
    public void readAccountsByClientId() {
        List<AccountDto> result = service.readAccountsByClientId(clientId);
        assertEquals(2, result.size());
    }
}