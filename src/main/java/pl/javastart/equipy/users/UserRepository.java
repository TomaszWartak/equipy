package pl.javastart.equipy.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    ArrayList<User> findUserByLastNameContainingIgnoreCase(String lastName);

    Optional<User> findByPesel(String pesel);

}
