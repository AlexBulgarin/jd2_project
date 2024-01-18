package by.sep.dto;

import java.time.LocalDateTime;

public class TransactionDto {
    private double sum;
    private LocalDateTime transactionDateTime;
    private String senderIban;
    private String recipientIban;

    public TransactionDto() {
    }

    public TransactionDto(double sum, LocalDateTime transactionDateTime, String senderIban, String recipientIban) {
        this.sum = sum;
        this.transactionDateTime = transactionDateTime;
        this.senderIban = senderIban;
        this.recipientIban = recipientIban;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getSenderIban() {
        return senderIban;
    }

    public void setSenderIban(String senderIban) {
        this.senderIban = senderIban;
    }

    public String getRecipientIban() {
        return recipientIban;
    }

    public void setRecipientIban(String recipientIban) {
        this.recipientIban = recipientIban;
    }
}
