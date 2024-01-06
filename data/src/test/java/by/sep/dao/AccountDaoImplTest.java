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
}