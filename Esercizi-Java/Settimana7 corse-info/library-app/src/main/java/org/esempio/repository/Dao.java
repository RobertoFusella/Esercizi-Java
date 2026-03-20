package org.esempio.repository;

import java.util.List;
import java.util.Optional;

public interface Dao<Book> {
    Optional<Book> findById(long id);
    public List<Book> findAll();
    Book create(Book book);
    Book update(Book book);
    int[] update(List<Book> books);
    int delete(Book book);
}
