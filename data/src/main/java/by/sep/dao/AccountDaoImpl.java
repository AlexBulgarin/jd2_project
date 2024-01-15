package by.sep.dao;

import by.sep.pojo.Account;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao{
    @Autowired
    protected AccountDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Account> readAccountsByClientId(String clientId) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT a FROM Account a JOIN a.product p " +
                        "JOIN p.clients c WHERE c.id = :clientId", Account.class)
                .setParameter("clientId", clientId)
                .list();
    }
}
