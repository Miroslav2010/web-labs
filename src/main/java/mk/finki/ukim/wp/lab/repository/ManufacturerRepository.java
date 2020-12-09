package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.models.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {
    public List<Manufacturer> findAll() {
        return DataHolder.manufacturerList;
    }
    public Optional<Manufacturer> findById(Long id) {
        return DataHolder.manufacturerList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}
