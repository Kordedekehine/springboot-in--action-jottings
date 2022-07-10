package brain.springframework.bookapp.repository;

import brain.springframework.bookapp.entity.Reader;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * there’s no need to write an implementation of ReaderRepository. Because it extends JpaRepository, Spring Data JPA will automatically
 * create an implementation of it at runtime. This affords you 18 methods for working
 * with Reader entities.
 */

public interface ReaderRepository extends JpaRepository<Reader,String> {
  //  UserDetails findOne(String username);


    Optional<Reader> findByUsername(String username);
}
