package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.models.Balloon;
import mk.finki.ukim.wp.lab.models.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    public List<Manufacturer> findAll();
    public Manufacturer save(String name,String address,String country);
}
