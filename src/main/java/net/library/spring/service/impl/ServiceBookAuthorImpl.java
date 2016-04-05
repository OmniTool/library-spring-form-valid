package net.library.spring.service.impl;

import net.library.spring.dao.DAOBookAuthor;
import net.library.spring.dto.*;
import net.library.spring.entities.*;
import net.library.spring.service.ServiceBookAuthor;
import net.library.spring.utils.converterDTO.impl.ConverterBookAuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceBookAuthorImpl extends ServiceBase<BookAuthorDTO, DAOBookAuthor, BookAuthor> implements ServiceBookAuthor {

    @Autowired
    public ServiceBookAuthorImpl(DAOBookAuthor dao, ConverterBookAuthorDTO converter) {
        super(dao, converter);
    }
    public List<BookDTO> searchBooksByAuthor(AuthorDTO author) {
        List<BookDTO> booksDTO = new ArrayList<>();
        for (Book book : getDao().searchBooksByAuthor(author.getEntity()))
            booksDTO.add(new BookDTO(book));
        return booksDTO;
    }
    public List<AuthorDTO> searchAuthorsByBook(BookDTO book) {
        List<AuthorDTO> authorsDTO = new ArrayList<>();
        for (Author author : getDao().searchAuthorsByBook(book.getEntity()))
            authorsDTO.add(new AuthorDTO(author));
        return authorsDTO;
    }
}