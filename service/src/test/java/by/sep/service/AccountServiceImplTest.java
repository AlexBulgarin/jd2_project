package by.sep.service;

import by.sep.TestServiceConfiguration;
import by.sep.dto.AccountDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServiceConfiguration.class)
public class AccountServiceImplTest {
    @Autowired
    AccountService service;

    @Test
    public void testReadAccountsByClientId() {
        List<AccountDto> list = service.readAccountsByClientId("359983b3-58b3-426c-bdc6-c66a2f6bde62");
        System.out.println(list.size());
        System.out.println(list.get(0).getIban());
        System.out.println(list.get(0).getBalance());
        System.out.println(list.get(0).getCards().get(0).getNumber());

    }
}