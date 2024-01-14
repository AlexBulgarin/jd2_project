package by.sep.pojo.product;

import by.sep.pojo.Account;
import by.sep.pojo.Client;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "t_product")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @UuidGenerator
    @Column(name = "product_id", nullable = false)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "duration_in_month", nullable = false)
    private Integer durationInMonth;
    @ManyToMany(mappedBy = "products",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Client> clients = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public Product() {
    }

    public Product(String id, String name, String description, Integer durationInMonth) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationInMonth = durationInMonth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationInMonth() {
        return durationInMonth;
    }

    public void setDurationInMonth(Integer durationInMonth) {
        this.durationInMonth = durationInMonth;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!Objects.equals(id, product.id)) return false;
        if (!Objects.equals(name, product.name)) return false;
        if (!Objects.equals(description, product.description)) return false;
        return Objects.equals(durationInMonth, product.durationInMonth);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (durationInMonth != null ? durationInMonth.hashCode() : 0);
        return result;
    }
}
