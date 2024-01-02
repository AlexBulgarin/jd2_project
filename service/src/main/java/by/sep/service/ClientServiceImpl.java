package by.sep.service;

import by.sep.dao.ClientLoginDao;
import by.sep.dto.CreateClientDto;
import by.sep.pojo.Client;
import by.sep.pojo.ClientLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientLoginDao dao;

    @Override
    public void createClient(CreateClientDto createClientDto) {
        Client client = new Client(
                null,
                createClientDto.getFirstName(),
                createClientDto.getLastName());
        ClientLogin clientLogin = new ClientLogin(
                null,
                createClientDto.getLogin(),
                createClientDto.getPassword(),
                createClientDto.getEmail());
        clientLogin.setClient(client);
        dao.create(clientLogin);
    }
}
