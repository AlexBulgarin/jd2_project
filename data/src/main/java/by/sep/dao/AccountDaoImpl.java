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
    public List<Account> readAllByProductId(String productId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select a from Account a where a.product.id = :productId",
                        Account.class)
                .setParameter("productId", productId)
                .list();
    }
}
