package application.model;

import application.model.entities.Movie;
import application.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    //@Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<User> findByUsername(String userName);
}
