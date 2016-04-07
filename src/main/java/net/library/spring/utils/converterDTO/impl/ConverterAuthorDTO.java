package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dao.*;
import net.library.spring.dto.AuthorDTO;
import net.library.spring.entities.*;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterAuthorDTO implements ConverterEntityDTO<Author, AuthorDTO> {

    @Autowired private DAOAuthor daoAuthor;
    @Autowired private DAOBook daoBook;
    @Autowired private DAOBookAuthor daoBookAuthor;

    @Override
    public AuthorDTO packEntityToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setSecondName(author.getSecondName());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setMiddleName(author.getMiddleName());
        authorDTO.setBirthYear(author.getBirthYear());
        authorDTO.setBiography(author.getBiography());
        authorDTO.setBooksIdList(bookAuthorlistToIdList(author.getBooksList()));
        authorDTO.setId(author.getId());
        return authorDTO;
    }
    @Override
    public Author unpackEntityFromDTO(AuthorDTO authorDTO) {
        int id = authorDTO.getId();
        Author author = new Author();
        if (id != 0) {
            author = daoAuthor.getEntityById(id);
            author.getBooksList().clear();
        }
        author.setSecondName(authorDTO.getSecondName());
        author.setFirstName(authorDTO.getFirstName());
        author.setMiddleName(authorDTO.getMiddleName());
        author.setBirthYear(authorDTO.getBirthYear());
        author.setBiography(authorDTO.getBiography());
        for (BookAuthor bookAuthor : idListToBookAuthorList(authorDTO.getBooksIdList(), author))
            author.getBooksList().add(daoBookAuthor.searchEntityByName(bookAuthor).get(0));
        return author;
    }
    private List<BookAuthor> idListToBookAuthorList (List<Integer> idList, Author author) {
        List<BookAuthor> bookAuthorList = new ArrayList<>();
        if (idList != null) for (int bookId : idList) {
            BookAuthor bookAuthor = new BookAuthor(daoBook.getEntityById(bookId), author);
            bookAuthorList.add(bookAuthor);
        }
        return bookAuthorList;
    }
    private List<Integer> bookAuthorlistToIdList (List<BookAuthor> bookAuthorList) {
        List<Integer> idList = new ArrayList<>();
        for (BookAuthor bookAuthor : bookAuthorList) idList.add(bookAuthor.getBook().getId());
        return idList;
    }

}
