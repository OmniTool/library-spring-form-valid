package net.library.spring.service;

import net.library.spring.dto.*;

import java.util.List;

public interface ServiceBookAuthor extends Service<BookAuthorDTO, Integer> {

    List<BookDTO> searchBooksByAuthor(AuthorDTO entity);
    List<AuthorDTO> searchAuthorsByBook(BookDTO entity);
}