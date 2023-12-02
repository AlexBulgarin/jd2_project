package by.sep;

import by.sep.pojos.Client;
import by.sep.pojos.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Test {
    public static void main(String[] args) {
        Session session = EbankingSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Product product = new Product(null, "KartaFUN");
        session.save(product);
        transaction.commit();
        session.close();
    }
}
