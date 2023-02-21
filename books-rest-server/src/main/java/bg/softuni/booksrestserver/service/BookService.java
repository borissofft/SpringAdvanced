package bg.softuni.booksrestserver.service;

import bg.softuni.booksrestserver.model.dto.AuthorDto;
import bg.softuni.booksrestserver.model.dto.BookDto;
import bg.softuni.booksrestserver.model.entity.Book;
import bg.softuni.booksrestserver.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void deleteById(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }
    public Optional<BookDto> findBookById(Long bookId) {
        return this.bookRepository
                .findById(bookId)
                .map(this::map);
    }

    public List<BookDto> getAllBooks() {
        return this.bookRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private BookDto map(Book book) {
        AuthorDto authorDto = new AuthorDto()
                .setName(book.getAuthor().getName());
        return new BookDto()
                .setId(book.getId())
                .setAuthor(authorDto)
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle());
    }

}
