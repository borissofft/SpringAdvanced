package bg.softuni.booksjsclient.web;

import bg.softuni.booksjsclient.model.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @GetMapping()
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId) {


        return null;
    }

}

// 2:06:00