package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.models.Balloon;
import mk.finki.ukim.wp.lab.models.Manufacturer;
import mk.finki.ukim.wp.lab.models.Type;
import mk.finki.ukim.wp.lab.models.exceptions.BalloonNotFoundException;
import mk.finki.ukim.wp.lab.models.exceptions.ManufacturerNotFoundException;
import mk.finki.ukim.wp.lab.repository.jpa.BalloonRepository;
import mk.finki.ukim.wp.lab.repository.jpa.ManufacturerRepository;
import mk.finki.ukim.wp.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return balloonRepository.findByOrderByNameAsc();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescriptionOrderByName(text,text);
    }
    @Override
    public Optional<Balloon> findById(Long id) {
        return this.balloonRepository.findById(id);
    }
    @Override
    public Balloon add(String name, String description, Long manufacturerId, Type type) {

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        return this.balloonRepository.save(new Balloon(name,description, manufacturer,type));
    }
    @Override
    @Transactional
    public Balloon edit(Long id, String name, String description, Long manufacturerId, Type type) {

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        this.balloonRepository.findById(id).orElseThrow(() -> new BalloonNotFoundException(id));
        this.balloonRepository.deleteById(id);
        return this.balloonRepository.save(new Balloon(name,description, manufacturer,type));
    }
    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }
    @Override
    public List<Balloon> searchByType(String text){
        List<Balloon> result = balloonRepository.findByTypeOrderByName(Type.valueOf(text));
        return result;
    }

    @Override
    public List<Balloon> findSthLikeDescOrName(String text) {
        return this.balloonRepository.findByNameContainsOrDescriptionContains(text,text);
    }

    @Override
    public List<Balloon> findSthLikeName(String text) {
        return this.balloonRepository.findByNameContains(text);
    }
}
