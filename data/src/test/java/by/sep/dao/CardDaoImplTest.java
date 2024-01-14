package by.sep.dao;

import by.sep.TestDataConfiguration;
import by.sep.pojo.Card;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class CardDaoImplTest {
    @Autowired
    CardDao dao;
    @Autowired
    DataSource dataSource;
    Connection connection;
    String testNumber = "1111 1111 1111 1111";
    LocalDate testExpiryDate = LocalDate.now();
    String testCvv = "111";

    @Before
    public void setUp() throws Exception {
        connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("INSERT INTO t_card " +
                "(card_number, expiry_date, cvv) " +
                "VALUES('" +
                testNumber + "', '" +
                testExpiryDate + "', '" +
                testCvv + "');");
    }

    @After
    public void tearDown() throws Exception {
        connection.createStatement().executeUpdate("DELETE FROM t_card");
        connection.createStatement().executeUpdate("DELETE FROM t_account");
        connection.close();
    }

    @Test
    public void testCreateCard() throws SQLException {
        testNumber = "2222 2222 2222 2222";
        String id = dao.create(new Card(
                testNumber, testExpiryDate, testCvv));

        assertNotNull(id);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_card");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);
    }

    @Test
    public void testReadCard() {
        Card card = dao.read(Card.class, testNumber);

        assertNotNull(card);
        assertEquals(testNumber, card.getNumber());
        assertEquals(testExpiryDate, card.getExpiryDate());
        assertEquals(testCvv, card.getCvv());
    }

    @Test
    public void testUpdateCard() {
        Card card = dao.read(Card.class, testNumber);
        LocalDate newTestExpiryDate = LocalDate.now().plusDays(1);
        String newTestCvv = "222";

        card.setExpiryDate(newTestExpiryDate);
        card.setCvv(newTestCvv);
        boolean result = dao.update(card);
        card = dao.read(Card.class, testNumber);

        assertTrue(result);
        assertEquals(newTestExpiryDate, card.getExpiryDate());
        assertEquals(newTestCvv, card.getCvv());
    }


    @Test
    public void testDeleteCard() throws SQLException {
        Card card = dao.read(Card.class, testNumber);
        boolean result = dao.delete(card);

        assertTrue(result);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_card");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);
    }

    @Test
    public void testReadAllByIban() throws SQLException {
        String testIban = Iban.random().toString();
        connection.createStatement().executeUpdate("INSERT INTO t_account " +
                "(account_iban, balance, currency_name, opening_date) " +
                "VALUES('" +
                testIban + "', " +
                "111.11, " +
                "'USD', " +
                "NOW());");
        connection.createStatement().executeUpdate("UPDATE t_card " +
                "SET account_iban='" + testIban + "' " +
                "WHERE card_number='" + testNumber + "';");
        List<Card> cards = dao.readAllByIban(testIban);

        assertNotNull(cards);
        assertEquals(1, cards.size());
    }
}