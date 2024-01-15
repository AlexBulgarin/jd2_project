package by.sep.dao;

import by.sep.pojo.Card;

import java.util.List;

public interface CardDao extends BaseDao<Card>{
    List<Card> readCardsByIban(String iban);
}
