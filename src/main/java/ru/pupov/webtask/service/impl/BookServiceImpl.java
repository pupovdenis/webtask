package ru.pupov.webtask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pupov.webtask.model.Book;
import ru.pupov.webtask.repository.BookRepository;
import ru.pupov.webtask.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public List<Book> getAllPrivateBooks() {
        return bookRepository.findAllByIsPrivateOrderByReleaseDateDesc(true);
    }

    @Override
    public List<Book> getAllPublicBooks() {
        return bookRepository.findAllByIsPrivateOrderByReleaseDateDesc(false);
    }
}
