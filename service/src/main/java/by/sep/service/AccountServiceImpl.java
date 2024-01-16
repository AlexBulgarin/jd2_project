package by.sep.service;

import by.sep.dao.AccountDao;
import by.sep.dao.CardDao;
import by.sep.dto.AccountDto;
import by.sep.dto.CardDto;
import by.sep.pojo.Account;
import by.sep.pojo.Card;
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
    @Autowired
    ProductService productService;

    @Override
    public List<AccountDto> readAccountsByClientId(String clientId) {
        List<Account> accounts = accountDao.readAccountsByClientId(clientId);
        List<AccountDto> accountDtos = new ArrayList<>();
        accounts.forEach(account -> {
            List<Card> cards = cardDao.readCardsByIban(account.getIban());
            List<CardDto> cardDtos = cards.stream()
                    .map(card -> new CardDto(card.getNumber(), card.getExpiryDate(), card.getCvv()))
                    .toList();
            AccountDto accountDto = new AccountDto(
                    account.getIban(),
                    productService.readById(account.getProduct().getId()),
                    account.getBalance(),
                    account.getCurrencyName(),
                    cardDtos
            );
            accountDtos.add(accountDto);
        });
        return accountDtos;
    }
}