package by.sep.service;

import by.sep.TestServiceConfiguration;
import by.sep.dto.CreateLoanDto;
import by.sep.pojo.product.Loan;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
public class ProductServiceImplTest {
    @Autowired
    ProductService service;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateProduct() {
        CreateLoanDto createLoanDto = new CreateLoanDto(null, "SuperLoan", "description",12,0.15,100.00);
        service.createProduct(createLoanDto);
    }
}