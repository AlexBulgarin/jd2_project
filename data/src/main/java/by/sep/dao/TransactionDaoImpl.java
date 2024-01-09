package by.sep.dao;

import by.sep.pojo.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TransactionDaoImpl extends BaseDaoImpl<Transaction> implements TransactionDao {
    protected TransactionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
