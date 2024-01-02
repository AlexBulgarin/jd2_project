package by.sep.dao;

import by.sep.pojo.ClientLogin;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ClientLoginDaoImp extends BaseDaoImpl<ClientLogin>
        implements ClientLoginDao {
    @Autowired
    public ClientLoginDaoImp(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
