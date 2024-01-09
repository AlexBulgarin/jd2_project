package by.sep.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "t_transaction")
public class Transaction {
    @Id
    @UuidGenerator
    @Column(name = "transaction_id", nullable = false)
    private String id;
    @Column(name = "sum", nullable = false)
    private Double sum;
    @Column(name = "transaction_date_time", nullable = false)
    private LocalDateTime transactionDateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_account_iban", nullable = false)
    private Account senderAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_account_iban", nullable = false)
    private Account recipientAccount;

    public Transaction() {
    }

    public Transaction(String id, Double sum, LocalDateTime transactionDateTime,
                       Account senderAccount, Account recipientAccount) {
        this.id = id;
        this.sum = sum;
        this.transactionDateTime = transactionDateTime;
        this.senderAccount = senderAccount;
        this.recipientAccount = recipientAccount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(sum, that.sum)) return false;
        if (!Objects.equals(transactionDateTime, that.transactionDateTime))
            return false;
        if (!Objects.equals(senderAccount, that.senderAccount))
            return false;
        return Objects.equals(recipientAccount, that.recipientAccount);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (transactionDateTime != null ? transactionDateTime.hashCode() : 0);
        result = 31 * result + (senderAccount != null ? senderAccount.hashCode() : 0);
        result = 31 * result + (recipientAccount != null ? recipientAccount.hashCode() : 0);
        return result;
    }
}
