package mk.finki.ukim.wp.lab.models;

import lombok.Data;

@Data
public class Manufacturer {
    private Long id;
    private String name;
    private String country;
    private String address;

    public Manufacturer(String name, String country, String address) {
        this.id = (long)Math.random()*1000;
        this.name = name;
        this.country = country;
        this.address = address;
    }
}
