package by.sep.dao;

import by.sep.TestDataConfiguration;
import by.sep.pojo.Account;
import by.sep.pojo.CurrencyName;
import org.iban4j.Iban;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class AccountDaoImplTest {
    @Autowired
    AccountDao dao;
    @Autowired
    DataSource dataSource;
    Connection connection;
    String testIban = Iban.random().toString();
    Double testBalance = 111.11;
    CurrencyName testCurrencyName = CurrencyName.USD;
    LocalDate testOpeningDate = LocalDate.now();

    @Before
    public void setUp() throws Exception {
        connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("INSERT INTO t_account " +
                "(account_iban, balance, currency_name, opening_date) " +
                "VALUES('" +
                testIban + "', " +
                testBalance + ", '" +
                testCurrencyName + "', '" +
                testOpeningDate + "');");
    }

    @After
    public void tearDown() throws Exception {
        connection.createStatement().executeUpdate("DELETE FROM t_account");
        connection.createStatement().executeUpdate("DELETE FROM t_client_product");
        connection.createStatement().executeUpdate("DELETE FROM t_client");
        connection.createStatement().executeUpdate("DELETE FROM t_product");
        connection.close();
    }

    @Test
    public void testCreateAccount() throws SQLException {
        String id = dao.create(new Account(
                Iban.random().toString(), testBalance, testCurrencyName, testOpeningDate));

        assertNotNull(id);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_account");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);
    }

    @Test
    public void testReadAccount() {
        Account account = dao.read(Account.class, testIban);

        assertNotNull(account);
        assertEquals(testIban, account.getIban());
        assertEquals(testBalance, account.getBalance());
        assertEquals(testCurrencyName, account.getCurrencyName());
        assertEquals(testOpeningDate, account.getOpeningDate());
    }

    @Test
    public void testUpdateAccount() {
        Account account = dao.read(Account.class, testIban);
        Double newTestBalance = 222.22;
        CurrencyName newTestCurrencyName = CurrencyName.EUR;
        LocalDate newTestOpeningDate = LocalDate.now().minusDays(1);

        account.setBalance(newTestBalance);
        account.setCurrencyName(newTestCurrencyName);
        account.setOpeningDate(newTestOpeningDate);
        boolean result = dao.update(account);
        account = dao.read(Account.class, testIban);

        assertTrue(result);
        assertEquals(newTestBalance, account.getBalance());
        assertEquals(newTestCurrencyName, account.getCurrencyName());
        assertEquals(newTestOpeningDate, account.getOpeningDate());
    }


    @Test
    public void testDeleteAccount() throws SQLException {
        Account account = dao.read(Account.class, testIban);
        boolean result = dao.delete(account);

        assertTrue(result);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_account");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);
    }

    @Test
    public void testReadAccountsByClientId() throws SQLException {
        String testClientId = UUID.randomUUID().toString();
        String testProductId = UUID.randomUUID().toString();
        connection.createStatement().executeUpdate("INSERT INTO t_client " +
                "(client_id, first_name, last_name, email) " +
                "VALUES ('" +
                testClientId + "', " +
                "'Test First Name', " +
                "'Test Last Name', " +
                "'Test@test.com');");
        connection.createStatement().executeUpdate("INSERT INTO t_product " +
                "(product_id, name, description, duration_in_month) " +
                "VALUES('" +
                testProductId + "', " +
                "'Test Product Name', " +
                "'Test Product Description', " +
                "1);");
        connection.createStatement().executeUpdate("INSERT INTO t_client_product " +
                "(client_id, product_id) " +
                "VALUES('" + testClientId + "', '" + testProductId + "');");
        connection.createStatement().executeUpdate("UPDATE t_account " +
                "SET product_id='" + testProductId + "' " +
                "WHERE account_iban='" + testIban + "';");
        List<Account> accounts = dao.readAccountsByClientId(testClientId);

        assertNotNull(accounts);
        assertEquals(1, accounts.size());
    }
}