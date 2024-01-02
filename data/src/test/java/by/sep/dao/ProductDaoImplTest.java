package by.sep.dao;

import by.sep.TestDataConfiguration;
import by.sep.pojo.product.Deposit;
import by.sep.pojo.product.Loan;
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
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataConfiguration.class)
@TestPropertySource(value = "classpath:test.liquibase.properties")
public class ProductDaoImplTest {
    @Autowired
    ProductDao dao;
    @Autowired
    DataSource dataSource;
    Connection connection;
    String testLoanId = UUID.randomUUID().toString();
    String testLoanName = "Test Loan Name";
    String testLoanDescription = "Test Loan Description";
    Integer testLoanDurationInMonth = 1;
    Double testLoanRate = 1.11;
    Double testLoanMaxSum = 11111.11;
    String testDepositId = UUID.randomUUID().toString();
    String testDepositName = "Test Deposit Name";
    String testDepositDescription = "Test Deposit Description";
    Integer testDepositDurationInMonth = 2;
    Double testDepositRate = 2.22;
    Double testDepositMinSum = 222.22;

    @Before
    public void setUp() throws Exception {
        connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("INSERT INTO t_product " +
                "(product_id, name, description, duration_in_month) " +
                "VALUES('" +
                testLoanId + "', '" +
                testLoanName + "', '" +
                testLoanDescription + "', " +
                testLoanDurationInMonth + ");");
        connection.createStatement().executeUpdate("INSERT INTO t_product " +
                "(product_id, name, description, duration_in_month) " +
                "VALUES('" +
                testDepositId + "', '" +
                testDepositName + "', '" +
                testDepositDescription + "', " +
                testDepositDurationInMonth + ");");
        connection.createStatement().executeUpdate("INSERT INTO t_product_loan " +
                "(fk_product_id, loan_rate, max_sum) " +
                "VALUES('" +
                testLoanId + "', " +
                testLoanRate + ", " +
                testLoanMaxSum + ");");
        connection.createStatement().executeUpdate("INSERT INTO t_product_deposit " +
                "(fk_product_id, deposit_rate, min_sum) " +
                "VALUES('" +
                testDepositId + "', " +
                testDepositRate + ", " +
                testDepositMinSum + ");");
    }

    @After
    public void tearDown() throws Exception {
        connection.createStatement().executeUpdate("DELETE FROM t_product_loan");
        connection.createStatement().executeUpdate("DELETE FROM t_product_deposit");
        connection.createStatement().executeUpdate("DELETE FROM t_product");
        connection.close();
    }

    @Test
    public void testCreateProduct() throws SQLException {
        String id = dao.create(new Loan(
                null, testLoanName, testDepositDescription,
                testLoanDurationInMonth, testLoanRate, testLoanMaxSum));
        String id2 = dao.create(new Deposit(
                null, testDepositName, testDepositDescription,
                testDepositDurationInMonth, testDepositRate, testDepositMinSum));

        assertNotNull(id);
        assertNotNull(id2);

        ResultSet resultSet = connection.createStatement().
                executeQuery("SELECT COUNT(*) FROM t_product");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(4, actualCount);

        resultSet = connection.createStatement().
                executeQuery("SELECT COUNT(*) FROM t_product_loan");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);

        resultSet = connection.createStatement().
                executeQuery("SELECT COUNT(*) FROM t_product_deposit");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);
    }

    @Test
    public void testReadProduct() {
        Loan loan = dao.read(Loan.class, testLoanId);
        Deposit deposit = dao.read(Deposit.class, testDepositId);

        assertNotNull(loan);
        assertEquals(testLoanId, loan.getId());
        assertEquals(testLoanName, loan.getName());
        assertEquals(testLoanDescription, loan.getDescription());
        assertEquals(testLoanDurationInMonth, loan.getDurationInMonth());
        assertEquals(testLoanRate, loan.getLoanRate());
        assertEquals(testLoanMaxSum, loan.getMaxSum());

        assertNotNull(deposit);
        assertEquals(testDepositId, deposit.getId());
        assertEquals(testDepositName, deposit.getName());
        assertEquals(testDepositDescription, deposit.getDescription());
        assertEquals(testDepositDurationInMonth, deposit.getDurationInMonth());
        assertEquals(testDepositRate, deposit.getDepositRate());
        assertEquals(testDepositMinSum, deposit.getMinSum());
    }

    @Test
    public void testUpdateProduct() {
        Loan loan = dao.read(Loan.class, testLoanId);
        String newTestLoanName = "New Test Loan Name";
        String newTestLoanDescription = "New Test Loan Description";
        Integer newTestLoanDurationInMonth = 3;
        Double newTestLoanRate = 3.33;
        Double newTestLoanMaxSum = 33333.33;
        loan.setName(newTestLoanName);
        loan.setDescription(newTestLoanDescription);
        loan.setDurationInMonth(newTestLoanDurationInMonth);
        loan.setLoanRate(newTestLoanRate);
        loan.setMaxSum(newTestLoanMaxSum);
        boolean result = dao.update(loan);

        Deposit deposit = dao.read(Deposit.class, testDepositId);
        String newTestDepositName = "New Test Deposit Name";
        String newTestDepositDescription = "New Test Deposit Description";
        Integer newTestDepositDurationInMonth = 4;
        Double newTestDepositRate = 4.44;
        Double newTestDepositMinSum = 444.44;
        deposit.setName(newTestDepositName);
        deposit.setDescription(newTestDepositDescription);
        deposit.setDurationInMonth(newTestDepositDurationInMonth);
        deposit.setDepositRate(newTestDepositRate);
        deposit.setMinSum(newTestDepositMinSum);
        boolean result1 = dao.update(deposit);

        assertTrue(result);
        assertEquals(newTestLoanName, loan.getName());
        assertEquals(newTestLoanDescription, loan.getDescription());
        assertEquals(newTestLoanDurationInMonth, loan.getDurationInMonth());
        assertEquals(newTestLoanRate, loan.getLoanRate());
        assertEquals(newTestLoanMaxSum, loan.getMaxSum());

        assertTrue(result1);
        assertEquals(newTestDepositName, deposit.getName());
        assertEquals(newTestDepositDescription, deposit.getDescription());
        assertEquals(newTestDepositDurationInMonth, deposit.getDurationInMonth());
        assertEquals(newTestDepositRate, deposit.getDepositRate());
        assertEquals(newTestDepositMinSum, deposit.getMinSum());
    }

    @Test
    public void testDeleteProduct() throws SQLException {
        Loan loan = dao.read(Loan.class, testLoanId);
        boolean result = dao.delete(loan);
        Deposit deposit = dao.read(Deposit.class, testDepositId);
        boolean result1 = dao.delete(deposit);

        assertTrue(result);
        assertTrue(result1);
        ResultSet resultSet = connection.createStatement().
                executeQuery("SELECT COUNT(*) FROM t_product");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);

        resultSet = connection.createStatement().
                executeQuery("SELECT COUNT(*) FROM t_product_loan");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);

        resultSet = connection.createStatement().
                executeQuery("SELECT COUNT(*) FROM t_product_deposit");
        resultSet.next();
        actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);
    }
}