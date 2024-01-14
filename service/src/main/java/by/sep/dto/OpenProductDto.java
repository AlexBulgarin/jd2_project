package by.sep.dto;

import by.sep.pojo.CurrencyName;

public class OpenProductDto {
    private Double balance;
    private CurrencyName currencyName;

    public OpenProductDto() {
    }

    public OpenProductDto(Double balance, CurrencyName currencyName) {
        this.balance = balance;
        this.currencyName = currencyName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public CurrencyName getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(CurrencyName currencyName) {
        this.currencyName = currencyName;
    }
}
