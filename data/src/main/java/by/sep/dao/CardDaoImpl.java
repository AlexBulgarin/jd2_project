package by.sep.dao;

import by.sep.pojo.Card;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CardDaoImpl extends BaseDaoImpl<Card> implements CardDao {
    @Autowired
    protected CardDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
