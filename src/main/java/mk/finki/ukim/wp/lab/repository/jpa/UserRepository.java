package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUsernameAndPassword(String username,String password);
    User findByUsername(String username);
    void deleteByUsername(String username);
}
