package by.sep.service;

import by.sep.dto.AccountDetailsDto;

import java.util.List;

public interface AccountService {
    List<AccountDetailsDto> readDetails(String clientId);
}
