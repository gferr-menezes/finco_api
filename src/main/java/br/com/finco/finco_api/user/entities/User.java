package br.com.finco.finco_api.user.entities;

import br.com.finco.finco_api.common.security.Cryptor;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String password;

    public User() {
    }

    public User(String name, String email, String password) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = encryptPassword(password);
    }

    private String encryptPassword(String password) {
        return Cryptor.encrypt(password);
    }

}
