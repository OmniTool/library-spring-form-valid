package net.library.spring.utils.converterDTO.impl;

import net.library.spring.dao.DAOAuthor;
import net.library.spring.dao.DAOBook;
import net.library.spring.dto.AuthorDTO;
import net.library.spring.entities.Author;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        authorDTO.setBooksIdList();
        authorDTO.setId(author.getId());
        return authorDTO;
    }
    @Override
    public Author unpackEntityFromDTO(AuthorDTO authorDTO) {
        int id = authorDTO.getId();
        Author author = (id == 0) ? new Author() : daoAuthor.getEntityById(id);
        author.setSecondName(authorDTO.getSecondName());
        author.setFirstName(authorDTO.getFirstName());
        author.setMiddleName(authorDTO.getMiddleName());
        author.setBirthYear(authorDTO.getBirthYear());
        author.setBiography(authorDTO.getBiography());
        author.setBooksList();
        return author;
    }

}
