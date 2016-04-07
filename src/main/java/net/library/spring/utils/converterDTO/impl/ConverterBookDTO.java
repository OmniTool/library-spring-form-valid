package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dao.DAOAuthor;
import net.library.spring.dao.DAOBook;
import net.library.spring.dao.DAOGenre;
import net.library.spring.dto.BookDTO;
import net.library.spring.entities.Book;
import net.library.spring.entities.BookAuthor;
import net.library.spring.entities.Genre;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterBookDTO implements ConverterEntityDTO<Book, BookDTO> {

    public static final int GENRE_DEFAULT_ID = 0;
    @Autowired private DAOAuthor daoAuthor;
    @Autowired private DAOBook daoBook;
    @Autowired private DAOGenre daoGenre;

    @Override
    public BookDTO packEntityToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPubYear(book.getPubYear());
        Genre genre = book.getGenre();
        bookDTO.setGenreId(genre != null ? genre.getId() : GENRE_DEFAULT_ID);
        bookDTO.setAuthorsIdList(bookAuthorlistToIdList(book.getAuthorsList()));
        bookDTO.setId(book.getId());
        return bookDTO;
    }
    @Override
    public Book unpackEntityFromDTO(BookDTO bookDTO) {
        int id = bookDTO.getId();
        Book book;
        if (id != 0) {
            book = daoBook.getEntityById(id);
            book.getAuthorsList().clear();
        } else {
            book = new Book();
            book.setAuthorsList(new ArrayList<BookAuthor>());
        }
        book.setGenre(daoGenre.getEntityById(bookDTO.getGenreId()));
        book.setTitle(bookDTO.getTitle());
        book.setPubYear(bookDTO.getPubYear());
        for (int authorId : bookDTO.getAuthorsIdList()) {
            book.getAuthorsList().add(new BookAuthor(book, daoAuthor.getEntityById(authorId)));
        }
        return book;
    }
    private List<Integer> bookAuthorlistToIdList (List<BookAuthor> bookAuthorList) {
        List<Integer> idList = new ArrayList<>();
        for (BookAuthor bookAuthor : bookAuthorList) idList.add(bookAuthor.getAuthor().getId());
        return idList;
    }

}
