package by.sep.dao;

import by.sep.pojo.product.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> readAllProducts() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Product", Product.class)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> readAllByClientId(String clientId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select p from Product p join p.clients c where c.id = :clientId",
                        Product.class)
                .setParameter("clientId", clientId)
                .list();
    }
}
