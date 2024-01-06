package by.sep.pojo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "t_card")
public class Card {
    @Id
    @Column(name = "card_number", nullable = false)
    private String number;
    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;
    @Column(name = "cvv", nullable = false)
    private String cvv;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account_iban")
    private Account account;

    public Card() {
    }

    public Card(String number, LocalDate expiryDate, String cvv) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!Objects.equals(number, card.number)) return false;
        if (!Objects.equals(expiryDate, card.expiryDate)) return false;
        return Objects.equals(cvv, card.cvv);
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (cvv != null ? cvv.hashCode() : 0);
        return result;
    }
}
