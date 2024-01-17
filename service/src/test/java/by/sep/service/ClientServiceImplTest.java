package by.sep.service;

import by.sep.TestServiceConfiguration;
import by.sep.dao.ClientDao;
import by.sep.dao.ClientLoginDao;
import by.sep.dto.ClientDto;
import by.sep.dto.ClientLoginDto;
import by.sep.pojo.Client;
import by.sep.pojo.ClientLogin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
public class ClientServiceImplTest {
    @InjectMocks
    ClientServiceImpl clientService;
    @Mock
    ClientDao clientDao;
    @Mock
    ClientLoginDao clientLoginDao;
    String testId = UUID.randomUUID().toString();
    String testName = "Test Name";
    String testLastName = "Test Last Name";
    String testEmail = "Test@test.com";

    @Before
    public void setUp() throws Exception {
        Client client = new Client(testId, testName, testLastName, testEmail);
        when(clientDao.readByEmail(testEmail)).thenReturn(client);
    }

    @Test
    public void createClient() {
        ClientDto clientDto = new ClientDto(testName, testLastName, testEmail);
        clientService.createClient(clientDto);
        verify(clientDao, times(1)).create(any(Client.class));
    }

    @Test
    public void createClientLogin() {
        ClientLoginDto clientLoginDto = new ClientLoginDto(
                testId, "Test Login", "Test Password",
                "Test Role", testEmail);
        clientService.createClientLogin(clientLoginDto);
        verify(clientLoginDao, times(1)).create(any(ClientLogin.class));
    }
}