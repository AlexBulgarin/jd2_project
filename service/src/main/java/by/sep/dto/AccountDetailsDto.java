package by.sep.dto;

import by.sep.pojo.CurrencyName;

import java.time.LocalDate;

public class AccountDetailsDto {
    private String iban;
    private String productType;
    private double balance;
    private CurrencyName currencyName;
    private String cardNumber;
    private LocalDate expiryDate;

    public AccountDetailsDto() {
    }

    public AccountDetailsDto(String iban, String productType, double balance, CurrencyName currencyName, String cardNumber, LocalDate expiryDate) {
        this.iban = iban;
        this.productType = productType;
        this.balance = balance;
        this.currencyName = currencyName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
