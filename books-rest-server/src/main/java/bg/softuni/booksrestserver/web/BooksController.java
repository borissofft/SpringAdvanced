package bg.softuni.booksrestserver.web;

import bg.softuni.booksrestserver.model.dto.BookDto;
import bg.softuni.booksrestserver.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final BookService bookService;


    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok(this.bookService.getAllBooks()); // This return status code 200 OK and Json array with DTOs
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId) {
        Optional<BookDto> theBook = this.bookService.findBookById(bookId);
        return theBook.map(ResponseEntity::ok) // Status Code: 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Status Code: 404 object not found
    }



}

// 1:31:25