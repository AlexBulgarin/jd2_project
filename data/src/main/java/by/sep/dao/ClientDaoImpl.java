package by.sep.dao;

import by.sep.pojo.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ClientDaoImpl extends BaseDaoImpl<Client> implements ClientDao {
    @Autowired
    public ClientDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
