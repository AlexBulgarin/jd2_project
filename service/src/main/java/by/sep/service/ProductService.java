package by.sep.service;

import by.sep.dto.CreateLoanDto;
import by.sep.dto.CreateProductDto;

public interface ProductService {
    void createProduct(CreateProductDto createProductDto);
    void createLoan(CreateLoanDto createLoanDto);

}
