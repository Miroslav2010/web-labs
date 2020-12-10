package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.models.ShoppingCart;
import mk.finki.ukim.wp.lab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    ShoppingCart findByUser(User user);
}
