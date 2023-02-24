package bg.softuni.books;

import bg.softuni.books.model.dto.BookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BooksRestDemo implements CommandLineRunner {

    private static final String API_URL = "http://localhost:8080/api/books";
    private static final Logger LOGGER = LoggerFactory.getLogger(BooksRestDemo.API_URL);
    private final RestTemplate restTemplate;

    @Autowired
    public BooksRestDemo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<BookDto[]> allBooksResponse =
                this.restTemplate.getForEntity(API_URL, BookDto[].class);

        if (allBooksResponse.hasBody()) {
            BookDto[] books = allBooksResponse.getBody();

            for (BookDto aBook : books) {
                LOGGER.info("Retrieved a book: {}", aBook);
            }
        }
    }
}

// 00:36:32