package by.sep.dao;

import by.sep.pojo.ClientLogin;

public interface ClientLoginDao extends BaseDao<ClientLogin> {
    ClientLogin findByLogin(String login);
}
