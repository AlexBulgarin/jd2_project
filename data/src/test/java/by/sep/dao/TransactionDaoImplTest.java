package by.sep.dao;

import by.sep.TestDataConfiguration;
import by.sep.pojo.*;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class TransactionDaoImplTest {
    @Autowired
    TransactionDao dao;
    @Autowired
    DataSource dataSource;
    Connection connection;
    String testId = UUID.randomUUID().toString();
    Double testSum = 111.11;
    LocalDateTime testTransactionDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    String testSenderIban = Iban.random().toString();
    Double testBalance = 222.22;
    CurrencyName testCurrencyName = CurrencyName.USD;
    LocalDate testOpeningDate = LocalDate.now();

    String testRecipientIban = Iban.random().toString();
    Account testSenderAccount = new Account(testSenderIban, testBalance, testCurrencyName, testOpeningDate);
    Account testRecipientAccount = new Account(testRecipientIban, testBalance, testCurrencyName, testOpeningDate);

    @Before
    public void setUp() throws Exception {
        connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("INSERT INTO t_account " +
                "(account_iban, balance, currency_name, opening_date) " +
                "VALUES('" +
                testSenderIban + "', " +
                testBalance + ", '" +
                testCurrencyName + "', '" +
                testOpeningDate + "');");
        connection.createStatement().executeUpdate("INSERT INTO t_account " +
                "(account_iban, balance, currency_name, opening_date) " +
                "VALUES('" +
                testRecipientIban + "', " +
                testBalance + ", '" +
                testCurrencyName + "', '" +
                testOpeningDate + "');");
        connection.createStatement().executeUpdate("INSERT INTO t_transaction" +
                "(transaction_id, sum, transaction_date_time, sender_account_iban, recipient_account_iban)" +
                "VALUES('" +
                testId + "', " +
                testSum + ", '" +
                testTransactionDateTime + "', '" +
                testSenderIban + "', '" +
                testRecipientIban + "');");
    }

    @After
    public void tearDown() throws Exception {
        connection.createStatement().executeUpdate("DELETE FROM t_transaction");
        connection.createStatement().executeUpdate("DELETE FROM t_account");
        connection.close();
    }

    @Test
    public void testCreateTransaction() throws SQLException {
        Transaction transaction = new Transaction(null, testSum, testTransactionDateTime,
                testSenderAccount, testRecipientAccount);

        String id = dao.create(transaction);

        assertNotNull(id);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_transaction");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);
    }

    @Test
    public void testReadTransaction() {
        Transaction transaction = dao.read(Transaction.class, testId);

        assertNotNull(transaction);
        assertEquals(testId, transaction.getId());
        assertEquals(testSum, transaction.getSum());
        assertEquals(testTransactionDateTime, transaction.getTransactionDateTime());
    }

    @Test
    public void testUpdateTransaction() {
        Transaction transaction = dao.read(Transaction.class, testId);
        Double newTestSum = 222.22;
        LocalDateTime newTestTransactionDateTime = LocalDateTime.now().minusHours(2).truncatedTo(ChronoUnit.SECONDS);
        transaction.setSum(newTestSum);
        transaction.setTransactionDateTime(newTestTransactionDateTime);

        boolean result = dao.update(transaction);
        transaction = dao.read(Transaction.class, testId);

        assertTrue(result);
        assertEquals(newTestSum, transaction.getSum());
        assertEquals(newTestTransactionDateTime, transaction.getTransactionDateTime());
    }

    @Test
    public void testDeleteTransaction() throws SQLException {
        Transaction transaction = dao.read(Transaction.class, testId);
        boolean result = dao.delete(transaction);

        assertTrue(result);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_transaction");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);
    }
}