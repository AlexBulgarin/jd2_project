package by.sep.service;

import by.sep.dao.AccountDao;
import by.sep.dao.CardDao;
import by.sep.dto.AccountDto;
import by.sep.pojo.Account;
import by.sep.pojo.Card;
import by.sep.pojo.product.Deposit;
import by.sep.pojo.product.Loan;
import by.sep.pojo.product.Product;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;
    @Autowired
    CardDao cardDao;

    @Override
    public List<AccountDto> readAccountsByClientId(String clientId) {
        List<Account> accounts = accountDao.readAccountsByClientId(clientId);
        List<AccountDto> accountDtos = new ArrayList<>();
        for (Account account : accounts) {
            List<Card> cards = cardDao.readCardsByIban(account.getIban());
            Product product = account.getProduct();
            Product realProduct = (Product) Hibernate.unproxy(product);
            String productName = realProduct.getName();
            double rate = 0.0;

            if (realProduct instanceof Loan loan) {
                rate = loan.getLoanRate();
            } else if (realProduct instanceof Deposit deposit) {
                rate = deposit.getDepositRate();
            }

            accountDtos.add(new AccountDto(
                    account.getIban(),
                    productName,
                    rate,
                    account.getBalance(),
                    account.getCurrencyName(),
                    cards
            ));
        }
        return accountDtos;
    }
}