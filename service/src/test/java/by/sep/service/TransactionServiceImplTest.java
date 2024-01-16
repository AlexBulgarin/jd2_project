package by.sep.service;

import by.sep.TestServiceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
public class TransactionServiceImplTest {
    @Autowired
    TransactionService service;

    @Test
    public void testMakeTransaction() {
        service.makeTransaction("LI1865207QPERNE8KY8KU", "5121 0044 8552 1890", 1.1);
    }
}