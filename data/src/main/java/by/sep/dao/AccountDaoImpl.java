package by.sep.dao;

import by.sep.pojo.Account;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AccountDaoImpl extends BaseDaoImpl<Account> implements AccountDao{
    @Autowired
    protected AccountDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
