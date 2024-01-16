package by.sep.service;

import by.sep.dto.OpenProductDto;
import by.sep.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void createProduct(ProductDto productDto);

    ProductDto readById(String id);

    void addProductToClient(String clientId, String productId, OpenProductDto openProductDto);
    void addNewCardToExistingAccount(String iban);

    <T extends ProductDto> List<T> readProducts(Class<T> clazz);
}
