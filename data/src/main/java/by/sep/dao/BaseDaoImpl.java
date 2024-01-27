package by.sep.dao;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    protected final SessionFactory sessionFactory;

    protected BaseDaoImpl(SessionFactory sessionFactory) {
        if (sessionFactory == null) {
            throw new IllegalArgumentException("An argument sessionFactory cannot be null");
        }
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String create(T t) {
        if (t == null) throw new IllegalArgumentException("Cannot create null entity");
        Session session = sessionFactory.getCurrentSession();
        return (String) session.save(t);
    }

    @Override
    public <S extends T> S read(Class<S> clazz, Serializable id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        Session session = sessionFactory.getCurrentSession();
        S entity = session.get(clazz, id);
        if (entity == null) throw new EntityNotFoundException(clazz.getSimpleName() + " not found for ID: " + id);
        return entity;
    }

    @Override
    public boolean update(T t) {
        if (t == null) throw new IllegalArgumentException("Cannot update null entity");
        Session session = sessionFactory.getCurrentSession();
        session.merge(t);
        return true;
    }

    @Override
    public boolean delete(T t) {
        if (t == null) throw new IllegalArgumentException("Cannot delete null entity");
        Session session = sessionFactory.getCurrentSession();
        session.remove(t);
        return true;
    }
}
