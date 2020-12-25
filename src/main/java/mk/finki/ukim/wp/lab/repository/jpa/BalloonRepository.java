package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.models.Balloon;
import mk.finki.ukim.wp.lab.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRepository extends JpaRepository<Balloon,Long> {
    List<Balloon> findByOrderByNameAsc();
    List<Balloon> findAllByNameOrDescriptionOrderByName(String name,String description);
    List<Balloon> findByTypeOrderByName(Type type);
    List<Balloon> findByNameContainsOrDescriptionContains(String name,String description);
    List<Balloon> findByNameContains(String name);
}
