package bg.softuni.booksjsclient.service;

import bg.softuni.booksjsclient.model.dto.AuthorDto;
import bg.softuni.booksjsclient.model.dto.BookDto;
import bg.softuni.booksjsclient.model.entity.Book;
import bg.softuni.booksjsclient.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
        return new BookDto().setId(book.getId())
                .setAuthor(authorDto)
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle());
    }

}
