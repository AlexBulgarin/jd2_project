package by.sep.dto;

import by.sep.pojo.CurrencyName;

import java.util.List;

public class AccountDto {
    private String iban;
    private ProductDto productDto;
    private double balance;
    private CurrencyName currencyName;
    private List<CardDto> cards;

    public AccountDto() {
    }

    public AccountDto(String iban, ProductDto productDto, double balance,
                      CurrencyName currencyName, List<CardDto> cards) {
        this.iban = iban;
        this.productDto = productDto;
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

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
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

    public List<CardDto> getCards() {
        return cards;
    }

    public void setCards(List<CardDto> cards) {
        this.cards = cards;
    }
}
