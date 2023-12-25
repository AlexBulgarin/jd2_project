package by.sep.service;

import by.sep.dao.BaseDao;
import by.sep.dto.ClientDto;
import by.sep.pojo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateClientServiceImpl implements CreateClientService {
    @Autowired
    @Qualifier(value = "clientDao")
    BaseDao<Client> dao;

    @Override
    public void createClient(ClientDto clientDto) {
        dao.create(new Client(null, clientDto.getFirstName(), clientDto.getLastName()));
    }
}
