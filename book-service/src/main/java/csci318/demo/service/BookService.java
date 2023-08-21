package csci318.demo.service;

import csci318.demo.model.Book;
import csci318.demo.model.Library;
import csci318.demo.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final RestTemplate restTemplate;

    BookService(BookRepository bookRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.restTemplate = restTemplate;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBook(String isbn) {
        return bookRepository.findById(isbn).orElseThrow(RuntimeException::new);
    }

    public List<Long> getAvailableLibraries1(String isbn) {
        return bookRepository.findById(isbn).orElseThrow(RuntimeException::new)
                .getAvailableLibraries();
    }

    public List<Library> getAvailableLibraries(String isbn) {
        final String url = "http://localhost:8081/libraries/";
        List<Library> libraries = new ArrayList<>();
        List<Long> ids = bookRepository.findById(isbn).orElseThrow(RuntimeException::new)
                .getAvailableLibraries();
        for (Long id : ids) {
            libraries.add(restTemplate.getForObject(url + id, Library.class));
        }
        return libraries;
    }

}
