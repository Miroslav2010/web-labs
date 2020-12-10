package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.models.Balloon;
import mk.finki.ukim.wp.lab.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRepository extends JpaRepository<Balloon,Long> {
    List<Balloon> findAllByNameOrDescription(String name,String description);
    List<Balloon> findByType(String type);
}
