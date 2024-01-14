package by.sep.service;

import by.sep.dao.AccountDao;
import by.sep.dto.AccountDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Override
    public List<AccountDetailsDto> readDetails(String clientId) {
        return null;
    }
}
