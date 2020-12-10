package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
