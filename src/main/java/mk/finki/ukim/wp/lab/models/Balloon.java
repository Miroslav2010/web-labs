package mk.finki.ukim.wp.lab.models;

import lombok.Data;

@Data
public class Balloon {
    private Long id;
    public String name;
    private String description;
    private Manufacturer manufacturer;
    public Type type;
    public Balloon(String name, String description,Manufacturer manufacturer,Type type) {
        this.id = (long)(Math.random()*1000);
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.type = type;
    }
}
