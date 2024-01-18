package by.sep.dao;

import by.sep.pojo.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TransactionDaoImpl extends BaseDaoImpl<Transaction> implements TransactionDao {
    protected TransactionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Transaction> readTransactionsByIban(String iban, int offset, int limit) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Transaction t " +
                        "WHERE t.senderAccount.iban = :iban OR t.recipientAccount.iban = :iban " +
                        "ORDER BY t.transactionDateTime DESC", Transaction.class)
                .setParameter("iban", iban)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }
}
