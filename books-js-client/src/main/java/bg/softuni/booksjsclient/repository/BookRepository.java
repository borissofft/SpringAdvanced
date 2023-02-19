package bg.softuni.booksjsclient.repository;

import bg.softuni.booksjsclient.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {



}
