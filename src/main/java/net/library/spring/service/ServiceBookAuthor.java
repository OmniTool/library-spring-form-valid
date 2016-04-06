package net.library.spring.service;

import net.library.spring.dto.AuthorDTO;
import net.library.spring.dto.BookAuthorDTO;
import net.library.spring.dto.BookDTO;
import net.library.spring.entities.BookAuthor;

import java.util.List;

public interface ServiceBookAuthor extends Service<BookAuthorDTO, Integer, BookAuthor> {

    List<BookDTO> searchBooksByAuthor(AuthorDTO entity);
    List<AuthorDTO> searchAuthorsByBook(BookDTO entity);

}