package net.library.spring.service;

import net.library.spring.entities.Book;
import net.library.spring.entities.Genre;

import java.util.List;

public interface ServiceBook extends Service<Book, Integer> {

    List<Book> searchBooksByGenre(Genre genre);

}
