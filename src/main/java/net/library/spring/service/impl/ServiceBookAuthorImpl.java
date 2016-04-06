package net.library.spring.service.impl;

import net.library.spring.dao.DAOBookAuthor;
import net.library.spring.dto.*;
import net.library.spring.entities.*;
import net.library.spring.service.*;
import net.library.spring.utils.converterDTO.impl.ConverterBookAuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceBookAuthorImpl extends ServiceBase<BookAuthorDTO, DAOBookAuthor, BookAuthor> implements ServiceBookAuthor {

    @Autowired ServiceAuthor serviceAuthor;
    @Autowired ServiceBook serviceBook;

    @Autowired
    public ServiceBookAuthorImpl(DAOBookAuthor dao, ConverterBookAuthorDTO converter) {
        super(dao, converter);
    }
    public List<BookDTO> searchBooksByAuthor(AuthorDTO author) {
        List<BookDTO> booksDTO = new ArrayList<>();
        for (Book book : getDao().searchBooksByAuthor(serviceAuthor.getConverterDTO().unpackEntityFromDTO(author)))
            booksDTO.add(serviceBook.getConverterDTO().packEntityToDTO(book));
        return booksDTO;
    }
    public List<AuthorDTO> searchAuthorsByBook(BookDTO book) {
        List<AuthorDTO> authorsDTO = new ArrayList<>();
        for (Author author : getDao().searchAuthorsByBook(serviceBook.getConverterDTO().unpackEntityFromDTO(book)))
            authorsDTO.add(serviceAuthor.getConverterDTO().packEntityToDTO(author));
        return authorsDTO;
    }
}