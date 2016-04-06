package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dao.DAOAuthor;
import net.library.spring.dao.DAOBook;
import net.library.spring.dao.DAOGenre;
import net.library.spring.dto.BookDTO;
import net.library.spring.entities.Book;
import net.library.spring.entities.BookAuthor;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterBookDTO implements ConverterEntityDTO<Book, BookDTO> {

    @Autowired private DAOAuthor daoAuthor;
    @Autowired private DAOBook daoBook;
    @Autowired private DAOGenre daoGenre;

    @Override
    public BookDTO packEntityToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPubYear(book.getPubYear());
        bookDTO.setGenreId(book.getGenre().getId());
        bookDTO.setAuthorsIdList(bookAuthorlistToIdList(book.getAuthorsList()));
        bookDTO.setId(book.getId());
        return bookDTO;
    }
    @Override
    public Book unpackEntityFromDTO(BookDTO bookDTO) {
        int id = bookDTO.getId();
        Book book = (id == 0) ? new Book() : daoBook.getEntityById(id);
        book.setTitle(bookDTO.getTitle());
        book.setPubYear(bookDTO.getPubYear());
        book.getAuthorsList().clear();
        book.setAuthorsList(idListToBookAuthorList(bookDTO.getAuthorsIdList(), book));
        book.setGenre(daoGenre.getEntityById(bookDTO.getGenreId()));
        return book;
    }
    private List<BookAuthor> idListToBookAuthorList (List<Integer> idList, Book book) {
        List<BookAuthor> bookAuthorList = new ArrayList<>();
        for (int authorId : idList) {
            BookAuthor bookAuthor = new BookAuthor(book, daoAuthor.getEntityById(authorId));
            bookAuthorList.add(bookAuthor);
        }
        return bookAuthorList;
    }
    private List<Integer> bookAuthorlistToIdList (List<BookAuthor> bookAuthorList) {
        List<Integer> idList = new ArrayList<>();
        for (BookAuthor bookAuthor : bookAuthorList) idList.add(bookAuthor.getAuthor().getId());
        return idList;
    }

}
