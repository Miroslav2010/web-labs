package mk.finki.ukim.wp.lab.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private LocalDateTime dateCreated;
    @ManyToMany
    private List<Order> orders;

    public ShoppingCart(){}
    public ShoppingCart(User user){
        //this.id = (long)(Math.random()*1000);
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.orders = new ArrayList<>();
    }
}
