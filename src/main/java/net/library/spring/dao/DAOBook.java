package net.library.spring.dao;

import net.library.spring.entities.Book;
import net.library.spring.entities.Genre;

import java.util.List;

public interface DAOBook extends DAO<Book, Integer> {

    List<Book> searchBooksByGenre(Genre genre);

}
