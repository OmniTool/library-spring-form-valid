package net.library.spring.service.impl;

import net.library.spring.dao.DAOBook;
import net.library.spring.dto.BookDTO;
import net.library.spring.dto.GenreDTO;
import net.library.spring.entities.Book;
import net.library.spring.service.ServiceBook;
import net.library.spring.service.ServiceGenre;
import net.library.spring.utils.converterDTO.impl.ConverterBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceBookImpl extends ServiceBase<BookDTO, DAOBook, Book> implements ServiceBook {

    @Autowired ServiceGenre serviceGenre;

    @Autowired
    public ServiceBookImpl(DAOBook dao, ConverterBookDTO converter) {
        super(dao, converter);
    }

    public List<BookDTO> searchBooksByGenre(GenreDTO genre) {
        List<BookDTO> entities = new ArrayList<>();
        for (Book book : getDao().searchBooksByGenre(serviceGenre.getConverterDTO().unpackEntityFromDTO(genre)))
            entities.add(getConverterDTO().packEntityToDTO(book));
        return entities;
    }
}
