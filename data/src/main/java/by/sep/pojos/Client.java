package by.sep.pojos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CLIENT")
public class Client {
    @Id
    @GenericGenerator(strategy = "uuid", name = "client_uuid")
    @GeneratedValue(generator = "client_uuid")
    private String id;
    private String name;
    private String surname;

}
