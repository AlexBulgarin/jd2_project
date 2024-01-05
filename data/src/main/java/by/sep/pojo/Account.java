package by.sep.pojo;

import by.sep.pojo.product.Product;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_account")
public class Account {
    @Id
    @Column(name = "account_iban", nullable = false)
    private String iban;
    @Column(name = "balance", nullable = false)
    private Double balance;
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;
    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product_id", nullable = false)
    private Product product;
    @OneToMany(mappedBy = "account")
    private List<Card> cards;

    public Account() {
    }

    public Account(String iban, Double balance, Currency currency, LocalDate openingDate) {
        this.iban = iban;
        this.balance = balance;
        this.currency = currency;
        this.openingDate = openingDate;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!Objects.equals(iban, account.iban)) return false;
        if (!Objects.equals(balance, account.balance)) return false;
        if (currency != account.currency) return false;
        return Objects.equals(openingDate, account.openingDate);
    }

    @Override
    public int hashCode() {
        int result = iban != null ? iban.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (openingDate != null ? openingDate.hashCode() : 0);
        return result;
    }
}
