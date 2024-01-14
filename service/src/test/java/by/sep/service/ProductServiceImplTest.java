package by.sep.service;

import by.sep.TestServiceConfiguration;
import by.sep.dto.DepositDto;
import by.sep.dto.LoanDto;
import by.sep.dto.ProductDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

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
    public void createProduct() {
    }

    @Test
    public void readProducts() {
        List<LoanDto> loans = service.readProducts(LoanDto.class);
        List<DepositDto> deposits = service.readProducts(DepositDto.class);
        List<ProductDto> products = service.readProducts(ProductDto.class);

        assertNotNull(products);
        assertNotNull(loans);
        assertNotNull(deposits);
        products.forEach(productDto -> System.out.println(productDto.getName()));
        loans.forEach(productDto -> System.out.println(productDto.getName()));
        deposits.forEach(productDto -> System.out.println(productDto.getName()));

    }

    @Test
    public void testAddProductToClient() {

    }
}