package by.sep.service;

import by.sep.dao.ClientDaoImpl;
import by.sep.dto.ClientDto;
import by.sep.pojo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateClientServiceImpl implements CreateClientService {
    @Autowired
    ClientDaoImpl clientDao;

    @Override
    public void createClient(ClientDto clientDto) {
        clientDao.create(new Client(null, clientDto.getFirstName(), clientDto.getLastName()));
    }
}
