package ru.pupov.webtask.service;

import org.springframework.stereotype.Service;
import ru.pupov.webtask.model.Book;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    List<Book> getAllPrivateBooks();

    List<Book> getAllPublicBooks();

    Book saveBook(Book book);

    Optional<Book> findById(long id);

    void delete(Book book);
}
