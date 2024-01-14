package by.sep.dao;

import by.sep.pojo.product.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {
    List<Product> readAllProducts();
    List<Product> readAllByClientId(String clientId);
}
