package bg.softuni.booksrestserver.web;

import bg.softuni.booksrestserver.model.dto.BookDto;
import bg.softuni.booksrestserver.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

//    @GetMapping()
//    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId) {
//
//
//        return null;
//    }

}

// 1:53:25
