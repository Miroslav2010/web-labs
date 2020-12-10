package mk.finki.ukim.wp.lab.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Balloon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String name;
    private String description;
    @ManyToOne
    private Manufacturer manufacturer;
    @Enumerated(EnumType.STRING)
    public Type type;
    public Balloon(){}
    public Balloon(String name, String description,Manufacturer manufacturer,Type type) {
        //this.id = (long)(Math.random()*1000);
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.type = type;
    }
}
