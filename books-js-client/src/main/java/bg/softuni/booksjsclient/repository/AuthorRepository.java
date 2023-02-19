package bg.softuni.booksjsclient.repository;

import bg.softuni.booksjsclient.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {



}
