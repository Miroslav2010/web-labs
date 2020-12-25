package mk.finki.ukim.wp.lab.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balloon balloon = (Balloon) o;
        return Objects.equals(id, balloon.id) &&
                Objects.equals(name, balloon.name) &&
                Objects.equals(description, balloon.description) &&
                Objects.equals(manufacturer, balloon.manufacturer) &&
                type == balloon.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, manufacturer, type);
    }
}
