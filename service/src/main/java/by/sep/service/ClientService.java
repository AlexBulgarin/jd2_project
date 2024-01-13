package by.sep.service;

import by.sep.dto.CreateClientDto;
import by.sep.dto.CreateClientLoginDto;

public interface ClientService {
    void createClient(CreateClientDto createClientDto);
    void createClientLogin(CreateClientLoginDto createClientLoginDto);
}
