package by.sep.dao;

import by.sep.TestDataConfiguration;
import by.sep.pojo.Client;
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
public class ClientDaoImplTest {
    @Autowired
    ClientDao dao;
    @Autowired
    DataSource dataSource;
    Connection connection;
    String testId = UUID.randomUUID().toString();
    String testFirstName = "Test First Name";
    String newTestFirstName = "New Test First Name";
    String testLastName = "Test Last Name";
    String newTestLastName = "New Test Last Name";

    @Before
    public void setUp() throws Exception {
        connection = dataSource.getConnection();
        connection.createStatement().executeUpdate("INSERT INTO t_client " +
                "(client_id, first_name, last_name) " +
                "VALUES ('" + testId + "', '" + testFirstName + "', '" + testLastName + "');");
    }

    @After
    public void tearDown() throws Exception {
        connection.createStatement().executeUpdate("DELETE FROM t_client");
        connection.close();
    }

    @Test
    public void testCreateClient() throws SQLException {
        String id = dao.create(new Client(null, testFirstName, testLastName));
        assertNotNull(id);

        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_client");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(2, actualCount);
    }

    @Test
    public void testReadClient() {
        Client client = dao.read(Client.class, testId);

        assertNotNull(client);
        assertEquals(testId, client.getId());
        assertEquals(testFirstName, client.getFirstName());
        assertEquals(testLastName, client.getLastName());
    }

    @Test
    public void testUpdateClient() {
        Client client = dao.read(Client.class, testId);
        client.setFirstName(newTestFirstName);
        client.setLastName(newTestLastName);
        boolean result = dao.update(client);

        assertTrue(result);
        assertEquals(newTestFirstName, client.getFirstName());
        assertEquals(newTestLastName, client.getLastName());
    }


    @Test
    public void testDeleteClient() throws SQLException {
        Client client = dao.read(Client.class, testId);
        boolean result = dao.delete(client);

        assertTrue(result);
        ResultSet resultSet = connection.createStatement()
                .executeQuery("SELECT COUNT(*) FROM t_client");
        resultSet.next();
        int actualCount = resultSet.getInt(1);
        assertEquals(0, actualCount);
    }
}