package by.sep;

import by.sep.pojos.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Test {
    public static void main(String[] args) {
        Session session = EbankingSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Client client = new Client(null, "Alex", "Bulgarin");
        session.save(client);
        transaction.commit();
        session.close();
    }
}
