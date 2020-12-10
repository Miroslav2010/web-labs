package mk.finki.ukim.wp.lab.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "shop_user")
public class User {
    @Id
    private String username;
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.carts = new ArrayList<>();
    }
}
