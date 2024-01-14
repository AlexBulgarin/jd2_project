package by.sep.service;

import by.sep.dto.ClientDto;
import by.sep.dto.ClientLoginDto;

public interface ClientService {
    void createClient(ClientDto clientDto);
    void createClientLogin(ClientLoginDto clientLoginDto);
}
