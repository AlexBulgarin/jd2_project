package by.sep.service;

import by.sep.dao.ProductDao;
import by.sep.dto.CreateDepositDto;
import by.sep.dto.CreateLoanDto;
import by.sep.dto.CreateProductDto;
import by.sep.pojo.product.Deposit;
import by.sep.pojo.product.Loan;
import by.sep.pojo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao dao;

    @Override
    public void createProduct(CreateProductDto createProductDto) {
        if (createProductDto == null) {
            throw new IllegalArgumentException("An argument createProductDto cannot be null");
        }
        Product product = new Product(null, createProductDto.getName(),
                createProductDto.getDescription(), createProductDto.getDurationInMonth());
        if (createProductDto instanceof CreateLoanDto) {
            Loan loan = new Loan();
            loan.setLoanRate(((CreateLoanDto) createProductDto).getLoanRate());
            loan.setMaxSum(((CreateLoanDto) createProductDto).getMaxSum());
            product = loan;
        } else if (createProductDto instanceof CreateDepositDto) {
            Deposit deposit = new Deposit();
            deposit.setDepositRate(((CreateDepositDto) createProductDto).getDepositRate());
            deposit.setMinSum(((CreateDepositDto) createProductDto).getMinSum());
            product = deposit;
        }
        dao.create(product);
    }

    @Override
    public void addProductToClient() {

    }
}
