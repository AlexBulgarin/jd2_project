package by.sep.service;

import by.sep.dao.AccountDao;
import by.sep.dao.ClientDao;
import by.sep.dao.ProductDao;
import by.sep.dto.*;
import by.sep.pojo.Account;
import by.sep.pojo.Card;
import by.sep.pojo.Client;
import by.sep.pojo.product.Deposit;
import by.sep.pojo.product.Loan;
import by.sep.pojo.product.Product;
import org.iban4j.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    ClientDao clientDao;
    @Autowired
    AccountDao accountDao;

    @Override
    public <T extends ProductDto> List<T> readProducts(Class<T> clazz) {
        List<Product> products = productDao.readAllProducts();
        if (clazz.equals(LoanDto.class)) return (List<T>) products.stream()
                .filter(product -> product instanceof Loan)
                .map(product -> new LoanDto(product.getId(), product.getName(), product.getDescription(),
                        product.getDurationInMonth(), ((Loan) product).getLoanRate(), ((Loan) product).getMaxSum()))
                .toList();
        if (clazz.equals(DepositDto.class)) return (List<T>) products.stream()
                .filter(product -> product instanceof Deposit)
                .map(product -> new DepositDto(product.getId(), product.getName(), product.getDescription(),
                        product.getDurationInMonth(), ((Deposit) product).getDepositRate(),
                        ((Deposit) product).getMinSum()))
                .toList();
        return (List<T>) products.stream()
                .filter(product -> !(product instanceof Loan) && !(product instanceof Deposit))
                .map(product -> new ProductDto(product.getId(), product.getName(),
                        product.getDescription(), product.getDurationInMonth()))
                .toList();
    }


    @Override
    public void addProductToClient(String clientId, String productId, OpenProductDto openProductDto) {
        Client client = clientDao.read(Client.class, clientId);
        Product product = productDao.read(Product.class, productId);
        Account account = new Account(Iban.random().toString(), openProductDto.getBalance(),
                openProductDto.getCurrencyName(), LocalDate.now());
        Card card = new Card(generateCardNumber(),
                LocalDate.now().plusMonths(product.getDurationInMonth()), generateCvv());
        card.setAccount(account);
        account.getCards().add(card);
        account.setProduct(product);
        product.getAccounts().add(account);
        client.getProducts().add(product);
        clientDao.update(client);
    }

    @Override
    public void addNewCardToExistingAccount(String iban) {
        Account account = accountDao.read(Account.class, iban);
        Card card = new Card(generateCardNumber(),
                LocalDate.now().plusMonths(account.getProduct().getDurationInMonth()),
                generateCvv());
        account.getCards().add(card);
        card.setAccount(account);
        accountDao.update(account);

    }

    private String generateCardNumber() {
        var sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0) sb.append(" ");
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString().trim();
    }

    private String generateCvv() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

}
