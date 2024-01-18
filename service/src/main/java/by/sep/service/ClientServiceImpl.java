package by.sep.service;

import by.sep.dao.ClientDao;
import by.sep.dao.ClientLoginDao;
import by.sep.dto.ClientDto;
import by.sep.dto.ClientLoginDto;
import by.sep.pojo.Client;
import by.sep.pojo.ClientLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientDao clientDao;
    @Autowired
    ClientLoginDao clientLoginDao;

    @Override
    public void createClient(ClientDto clientDto) {
        if (clientDto == null) {
            throw new IllegalArgumentException("An argument createClientDto cannot be null");
        }
        Client client = new Client(
                null,
                clientDto.getFirstName(),
                clientDto.getLastName(),
                clientDto.getEmail());
        clientDao.create(client);
    }

    @Override
    public void createClientLogin(ClientLoginDto clientLoginDto) {
        if (clientLoginDto == null) {
            throw new IllegalArgumentException("An argument createClientLoginDto cannot be null");
        }
        Client client = clientDao.readByEmail(clientLoginDto.getEmail());
        ClientLogin clientLogin = new ClientLogin(
                client.getId(),
                clientLoginDto.getLogin(),
                clientLoginDto.getPassword());
        clientLogin.setRole("ROLE_USER");
        clientLogin.setClient(client);
        clientLoginDao.create(clientLogin);
    }
}
