package mk.finki.ukim.wp.lab.repository.impl;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.models.*;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {

    public List<Balloon> findAllBalloon(){
        sortBalloons();
        return DataHolder.balloonList;
    }

    public List<Balloon> findBalloonsByNameOrDescription(String text){
        return DataHolder.balloonList.stream().filter(b->b.getName().contains(text) || b.getDescription().contains(text)).collect(Collectors.toList());
    }

    public Optional<Balloon> findById(Long id) {
        return DataHolder.balloonList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public List<Balloon> findByNameOrDescription(String text) {
        return DataHolder.balloonList.stream().filter(r -> r.getName().equals(text)).collect(Collectors.toList());
    }
    public void deleteById(Long id) {
        DataHolder.balloonList.removeIf(i -> i.getId().equals(id));
    }
    public Optional<Balloon> add(String name,
                                 String description, Manufacturer manufacturer, Type type) {
        DataHolder.balloonList.removeIf(i -> i.getName().equals(name));
        Balloon product = new Balloon(name, description, manufacturer,type);
        DataHolder.balloonList.add(product);
        sortBalloons();
        return Optional.of(product);
    }
    public Optional<Balloon> edit(Long id, String name,
                                  String description, Manufacturer manufacturer, Type type) {
        DataHolder.balloonList.removeIf(i -> i.getId().equals(id));
        Balloon product = new Balloon(name, description, manufacturer, type);
        DataHolder.balloonList.add(product);
        sortBalloons();
        return Optional.of(product);
    }

    public void sortBalloons(){
        DataHolder.balloonList.sort(Comparator.comparing(Balloon::getName));
    }
    public List<Balloon> searchByType(String text){
        return DataHolder.balloonList.stream().filter(r -> r.getType().toString().equals(text)).collect(Collectors.toList());
    }
}
