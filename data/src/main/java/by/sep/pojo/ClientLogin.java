package by.sep.pojo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "t_client_login")
public class ClientLogin {
    @Id
    @Column(name = "client_id", nullable = false)
    private String id;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role")
    private String role;
    @OneToOne
    @MapsId
    @JoinColumn(name = "client_id")
    private Client client;

    public ClientLogin() {
    }

    public ClientLogin(String id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientLogin that = (ClientLogin) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(login, that.login)) return false;
        if (!Objects.equals(password, that.password)) return false;
        return Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
