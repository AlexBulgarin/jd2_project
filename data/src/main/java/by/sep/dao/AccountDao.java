package by.sep.dao;

import by.sep.pojo.Account;

import java.util.List;

public interface AccountDao extends BaseDao<Account>{
    List<Account> readAccountsByClientId(String clientId);
}
