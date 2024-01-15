package by.sep.service;

import by.sep.dto.AccountDto;

import java.util.List;

public interface AccountService {
    List<AccountDto> readAccountsByClientId(String clientId);
}
