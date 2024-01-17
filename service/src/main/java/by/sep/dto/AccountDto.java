package by.sep.dto;

import by.sep.pojo.Card;
import by.sep.pojo.CurrencyName;

import java.util.List;

public class AccountDto {
    private String iban;
    private String productName;
    private double rate;
    private double balance;
    private CurrencyName currencyName;
    private List<Card> cards;

    public AccountDto() {
    }

    public AccountDto(String iban, String productName, double rate,
                      double balance, CurrencyName currencyName, List<Card> cards) {
        this.iban = iban;
        this.productName = productName;
        this.rate = rate;
        this.balance = balance;
        this.currencyName = currencyName;
        this.cards = cards;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CurrencyName getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(CurrencyName currencyName) {
        this.currencyName = currencyName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
