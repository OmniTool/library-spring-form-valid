package net.library.spring.service.impl;

import net.library.spring.dao.DAOBook;
import net.library.spring.entities.*;
import net.library.spring.dto.*;
import net.library.spring.service.ServiceBook;
import net.library.spring.utils.converterDTO.impl.ConverterBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceBookImpl extends ServiceBase<BookDTO, DAOBook, Book> implements ServiceBook {

    @Autowired
    public ServiceBookImpl(DAOBook dao, ConverterBookDTO converter) {
        super(dao, converter);
    }
    public List<BookDTO> searchBooksByGenre(GenreDTO genre) {
        List<BookDTO> entities = new ArrayList<>();
        for (Book book : getDao().searchBooksByGenre(genre.getEntity()))
            entities.add(new BookDTO(book));
        return entities;
    }
}
