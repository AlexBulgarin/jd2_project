package by.sep.dto;

import java.time.LocalDate;

public class CardDto {
    private String number;
    private LocalDate expiryDate;
    private String cvv;

    public CardDto() {
    }

    public CardDto(String number, LocalDate expiryDate, String cvv) {
        this.number = number;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
