package by.sep.service;

import by.sep.TestServiceConfiguration;
import by.sep.dao.AccountDao;
import by.sep.dao.ClientDao;
import by.sep.dao.ProductDao;
import by.sep.dto.DepositDto;
import by.sep.dto.LoanDto;
import by.sep.dto.OpenProductDto;
import by.sep.dto.ProductDto;
import by.sep.pojo.Account;
import by.sep.pojo.Client;
import by.sep.pojo.CurrencyName;
import by.sep.pojo.product.Loan;
import by.sep.pojo.product.Product;
import org.iban4j.Iban;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
public class ProductServiceImplTest {
    @InjectMocks
    ProductServiceImpl service;
    @Mock
    ProductDao productDao;
    @Mock
    ClientDao clientDao;
    @Mock
    AccountDao accountDao;
    String testId = UUID.randomUUID().toString();
    String productTestId = UUID.randomUUID().toString();
    String loanTestId = UUID.randomUUID().toString();
    String testIban = Iban.random().toString();
    Client client;
    Product product;
    Loan loan;
    Account account;

    @Before
    public void setUp() throws Exception {
        product = new Product(productTestId, "test Name",
                "test Description", 1);
        loan = new Loan(loanTestId, "test Name",
                "test Description", 2, 1.1, 200.50);
        List<Product> mockProducts = List.of(product, loan);
        client = new Client(testId, "testName", "testLastName", "testEmail");
        account = new Account(testIban, 333.33, CurrencyName.BYN, LocalDate.now());
        account.setProduct(product);
        when(productDao.readAllProducts()).thenReturn(mockProducts);
        when(clientDao.read(Client.class, testId)).thenReturn(client);
        when(productDao.read(Product.class, productTestId)).thenReturn(product);
        when(productDao.read(Product.class, loanTestId)).thenReturn(loan);
        when(accountDao.read(Account.class, testIban)).thenReturn(account);
    }

    @Test
    public void readProducts() {
        List<ProductDto> result = service.readProducts(ProductDto.class);
        List<LoanDto> result1 = service.readProducts(LoanDto.class);
        List<DepositDto> result2 = service.readProducts(DepositDto.class);
        assertEquals(1, result.size());
        assertEquals(1, result1.size());
        assertEquals(0, result2.size());
    }

    @Test
    public void addProductToClient() {
        service.addProductToClient(testId, productTestId,
                new OpenProductDto(111.11, CurrencyName.USD));
        service.addProductToClient(testId, loanTestId,
                new OpenProductDto(222.22, CurrencyName.EUR));
        verify(clientDao, times(2)).update(client);
        assertEquals(2, client.getProducts().size());
        assertTrue(client.getProducts().contains(product));
        assertTrue(client.getProducts().contains(loan));
    }

    @Test
    public void addNewCardToExistingAccount() {
        service.addNewCardToExistingAccount(testIban);

        verify(accountDao, times(1)).update(account);
        assertEquals(1, account.getCards().size());
    }
}