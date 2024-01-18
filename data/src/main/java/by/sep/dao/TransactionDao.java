package by.sep.dao;

import by.sep.pojo.Transaction;

import java.util.List;

public interface TransactionDao extends BaseDao<Transaction> {
    List<Transaction> readTransactionsByIban(String iban, int offset, int limit);
}
