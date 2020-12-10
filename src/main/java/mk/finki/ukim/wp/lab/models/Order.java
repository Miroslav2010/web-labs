package mk.finki.ukim.wp.lab.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shop_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String balloonColor;
    private String balloonSize;
    @ManyToOne
    private User user;

    public Order(){}

    public Order(String balloonColor, String balloonSize,User user) {
        //this.orderId = (long)(Math.random()*1000);
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.user = user;
    }
}
