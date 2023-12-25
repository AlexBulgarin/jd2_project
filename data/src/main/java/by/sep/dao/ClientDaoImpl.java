package by.sep.dao;

import by.sep.pojo.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "clientDao")
@Transactional
public class ClientDaoImpl extends BaseDao<Client> {
    @Autowired
    public ClientDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}