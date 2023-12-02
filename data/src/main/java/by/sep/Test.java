package by.sep;

import by.sep.pojos.Client;
import by.sep.pojos.Product;
import by.sep.util.EbankingSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        Session session = EbankingSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Product product = new Product(UUID.randomUUID().toString(), "KartaFUN");
        Client client = new Client(UUID.randomUUID().toString(), "Alex", "Bulgarin");
        product.getClients().add(client);
        client.getProducts().add(product);
        session.save(client);
        transaction.commit();
        session.close();
        session = EbankingSessionFactory.getSessionFactory().openSession();

    }
}
