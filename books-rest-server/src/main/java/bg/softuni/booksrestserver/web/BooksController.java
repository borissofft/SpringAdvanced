package bg.softuni.booksrestserver.web;

import bg.softuni.booksrestserver.model.dto.BookDto;
import bg.softuni.booksrestserver.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:63342")
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

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBookById(@PathVariable("id") Long bookId) {
        this.bookService.deleteById(bookId);
        return ResponseEntity.noContent().build(); // Status Code: 204 no content
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto newBook, // In postman we have to say that the request is in JSON format
                                              UriComponentsBuilder uriComponentsBuilder) { // Get body with Json format and try to map it to BookDto.class automatically

        long newBookId = bookService.createBook(newBook);

        return ResponseEntity.created(uriComponentsBuilder.
                        path("/api/books/{id}").build(newBookId))
                        .build();
    }

}

// 1:06:25
