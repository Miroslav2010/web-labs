package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.models.Manufacturer;
import mk.finki.ukim.wp.lab.repository.impl.InMemoryManufacturerRepository;
import mk.finki.ukim.wp.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.wp.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }
}
