package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.models.Balloon;
import mk.finki.ukim.wp.lab.models.Type;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);
    List<Balloon> searchByType(String text);
    Optional<Balloon> findById(Long id);
    Optional<Balloon> add(String name, String description, Long manufacturerId, Type tip);
    Optional<Balloon> edit(Long id, String name, String description, Long manufacturerId,Type type);
    void deleteById(Long id);
}
