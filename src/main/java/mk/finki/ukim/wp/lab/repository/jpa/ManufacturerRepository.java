package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
}