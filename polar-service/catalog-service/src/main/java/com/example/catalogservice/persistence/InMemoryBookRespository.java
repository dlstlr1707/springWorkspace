package com.example.catalogservice.persistence;

import com.example.catalogservice.domain.Book;
import com.example.catalogservice.domain.BookRespository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
/*
//@Repository
public class InMemoryBookRespository implements BookRespository {
    // DB연결후 안써서 삭제함
    private static final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return existsByIsbn(isbn) ?
                Optional.of(books.get(isbn)) :
                Optional.empty();
    }

    @Override
    public Book save(Book book) {
        books.put(book.isbn(), book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        books.remove(isbn);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        // map에 키 가지고 있는지 확인
        return books.containsKey(isbn);
    }
}
*/