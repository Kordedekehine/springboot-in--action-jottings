package brain.springframework.bookapp.repository;

import brain.springframework.bookapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * By extending JpaRepository, ReadingListRepository inherits 18 methods for performing common persistence operations.
 * The JpaRepository interface is parameterized with two parameters: the domain type that the repository will work with, and the
 * type of its ID property
 */

public interface ReadingListRepository extends JpaRepository<Book,Long> {
    List<Book> findByReader(String reader);
}
