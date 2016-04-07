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

    @Override
    public AuthorDTO packEntityToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setSecondName(author.getSecondName());
        authorDTO.setFirstName(author.getFirstName());
        authorDTO.setMiddleName(author.getMiddleName());
        authorDTO.setBirthYear(author.getBirthYear());
        authorDTO.setBiography(author.getBiography());
        authorDTO.setBooksIdList(convertBookAuthorListToBookIdList(author.getBooksList()));
        authorDTO.setId(author.getId());
        return authorDTO;
    }
    @Override
    public Author unpackEntityFromDTO(AuthorDTO authorDTO) {
        int id = authorDTO.getId();
        Author author = getAuthor(id);
        author.setSecondName(authorDTO.getSecondName());
        author.setFirstName(authorDTO.getFirstName());
        author.setMiddleName(authorDTO.getMiddleName());
        author.setBirthYear(authorDTO.getBirthYear());
        author.setBiography(authorDTO.getBiography());
        for (int bookId : authorDTO.getBooksIdList()) {
            author.getBooksList().add(new BookAuthor(daoBook.getEntityById(bookId), author));
        }
        return author;
    }
    private Author getAuthor(int id) {
        Author author;
        if (id != 0) {
            author = daoAuthor.getEntityById(id);
            author.getBooksList().clear();
        } else {
            author = new Author();
            author.setBooksList(new ArrayList<BookAuthor>());
        }
        return author;
    }
    private List<Integer> convertBookAuthorListToBookIdList (List<BookAuthor> bookAuthorList) {
        List<Integer> idList = new ArrayList<>();
        for (BookAuthor bookAuthor : bookAuthorList) idList.add(bookAuthor.getBook().getId());
        return idList;
    }


}
