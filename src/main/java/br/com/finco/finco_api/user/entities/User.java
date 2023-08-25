package br.com.finco.finco_api.user.entities;

import br.com.finco.finco_api.common.security.Cryptor;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
     @Id()
     @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    private String email;

    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    public User() {
    }

    public User(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Profile getProfile() {
        return this.profile;
    }

    private String encryptPassword(String password) {
        return Cryptor.encrypt(password);
    }

}
