package by.sep.service;

import by.sep.dao.ClientDao;
import by.sep.dao.ClientLoginDao;
import by.sep.dto.CreateClientDto;
import by.sep.dto.CreateClientLoginDto;
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
    public void createClient(CreateClientDto createClientDto) {
        if (createClientDto == null) {
            throw new IllegalArgumentException("An argument createClientDto cannot be null");
        }
        Client client = new Client(
                null,
                createClientDto.getFirstName(),
                createClientDto.getLastName(),
                createClientDto.getEmail());
        clientDao.create(client);
    }

    @Override
    public void createClientLogin(CreateClientLoginDto createClientLoginDto) {
        if (createClientLoginDto == null) {
            throw new IllegalArgumentException("An argument createClientLoginDto cannot be null");
        }
        Client client = clientDao.readByEmail(createClientLoginDto.getEmail());
        ClientLogin clientLogin = new ClientLogin(
                client.getId(),
                createClientLoginDto.getLogin(),
                createClientLoginDto.getPassword());
        clientLogin.setRole("ROLE_USER");
        clientLogin.setClient(client);
        clientLoginDao.create(clientLogin);
    }
}
