package by.sep.dao;

import by.sep.pojo.Card;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CardDaoImpl extends BaseDaoImpl<Card> implements CardDao {
    @Autowired
    protected CardDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Card> readCardsByIban(String iban) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Card c JOIN c.account a WHERE a.iban = :iban",
                        Card.class)
                .setParameter("iban", iban)
                .list();
    }
}
