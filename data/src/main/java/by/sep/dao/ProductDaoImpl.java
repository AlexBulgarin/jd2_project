package by.sep.dao;

import by.sep.pojo.Product;
import org.hibernate.SessionFactory;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao{
    public ProductDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
