package by.sep.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    private final SessionFactory sessionFactory;

    public BaseDaoImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("An argument sessionFactory cannot be null");
        }
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String create(T t) {
        Session session = sessionFactory.getCurrentSession();
        return (String) session.save(t);
    }

    @Override
    public T read(Class<T> clazz, Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(clazz, id);
    }

    @Override
    public boolean update(T t) {
        if (t == null) return false;
        Session session = sessionFactory.getCurrentSession();
        session.merge(t);
        return true;
    }

    @Override
    public boolean delete(T t) {
        if (t == null) return false;
        Session session = sessionFactory.getCurrentSession();
        session.remove(t);
        return true;
    }
}
