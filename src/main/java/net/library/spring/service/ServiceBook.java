package net.library.spring.service;

import net.library.spring.dto.BookDTO;
import net.library.spring.dto.GenreDTO;
import net.library.spring.entities.Book;

import java.util.List;

public interface ServiceBook extends Service<BookDTO, Integer, Book> {

    List<BookDTO> searchBooksByGenre(GenreDTO genre);

}
