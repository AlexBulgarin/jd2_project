package by.sep.dao;

import by.sep.TestDataConfiguration;
import by.sep.pojo.Client;
import by.sep.pojo.ClientLogin;
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
public class ClientLoginDaoImpTest {
    @Autowired
    ClientLoginDao dao;
    @Autowired
    DataSource dataSource;
    Connection connection;
    String testId = UUID.randomUUID().toString();
    String testLogin = "Test Login";
    String testPassword = "Test Password";
    String testEmail = "Test@test.com";

    @Before
    public void setUp() throws Exception {
        connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("INSERT INTO t_client " +
                "(client_id, first_name, last_name) " +
                "VALUES ('" +
                testId + "', " +
                "'Test First Name', " +
                "'Test Last Name');");
        connection.createStatement().executeUpdate("INSERT INTO t_client_login " +
                "(fk_client_id, login, password, email) " +
                "VALUES ('" +
                testId + "', '" +
                testLogin + "', '" +
                testPassword + "', '" +
                testEmail + "');");
    }

    @After
    public void tearDown() throws Exception {
        connection.createStatement().executeUpdate("DELETE FROM t_client_login");
        connection.createStatement().executeUpdate("DELETE FROM t_client");
        connection.close();
    }

    @Test
    public void testCreateClientLogin() throws SQLException {
        Client client = new Client(null, "Test First Name", "Test Last Name");
        testLogin = "Test Login 1";
        ClientLogin clientLogin = new ClientLogin(null, testLogin, testPassword, testEmail);
        clientLogin.setClient(client);
        String id = dao.create(clientLogin);
        assertNotNull(id);

        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_client_login");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);
    }

    @Test
    public void testReadClientLogin() {
        ClientLogin clientLogin = dao.read(ClientLogin.class, testId);

        assertNotNull(clientLogin);
        assertEquals(testId, clientLogin.getId());
        assertEquals(testLogin, clientLogin.getLogin());
        assertEquals(testPassword, clientLogin.getPassword());
        assertEquals(testEmail, clientLogin.getEmail());
    }

    @Test
    public void testUpdateClientLogin() {
        ClientLogin clientLogin = dao.read(ClientLogin.class, testId);
        String newTestLogin = "New Test Login";
        String newTestPassword = "New Test Password";
        String newTestEmail = "NewTest@test.org";
        clientLogin.setLogin(newTestLogin);
        clientLogin.setPassword(newTestPassword);
        clientLogin.setEmail(newTestEmail);
        boolean result = dao.update(clientLogin);

        assertTrue(result);
        assertEquals(newTestLogin, clientLogin.getLogin());
        assertEquals(newTestPassword, clientLogin.getPassword());
        assertEquals(newTestEmail, clientLogin.getEmail());
    }

    @Test
    public void testDeleteClientLogin() throws SQLException {
        ClientLogin clientLogin = dao.read(ClientLogin.class, testId);
        boolean result = dao.delete(clientLogin);

        assertTrue(result);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_client_login");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);
    }
}