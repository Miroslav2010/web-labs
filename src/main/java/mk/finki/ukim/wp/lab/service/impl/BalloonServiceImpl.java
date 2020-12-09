package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.models.Balloon;
import mk.finki.ukim.wp.lab.models.Manufacturer;
import mk.finki.ukim.wp.lab.models.Type;
import mk.finki.ukim.wp.lab.models.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.wp.lab.repository.BalloonRepository;
import mk.finki.ukim.wp.lab.repository.ManufacturerRepository;
import mk.finki.ukim.wp.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {
    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAllBalloon();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findByNameOrDescription(text);
    }
    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonRepository.findById(id);
    }
    @Override
    public Optional<Balloon> add(String name, String description, Long manufacturerId, Type type) {

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        return this.balloonRepository.add(name,description, manufacturer,type);
    }
    @Override
    public Optional<Balloon> edit(Long id, String name, String description, Long manufacturerId, Type type) {

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        return this.balloonRepository.edit(id, name,description, manufacturer,type);
    }
    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }
    @Override
    public List<Balloon> searchByType(String text){
        return balloonRepository.searchByType(text);
    }
}
