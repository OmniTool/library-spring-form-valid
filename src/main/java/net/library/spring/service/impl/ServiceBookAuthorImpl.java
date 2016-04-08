package net.library.spring.service.impl;

import net.library.spring.dao.DAOBookAuthor;
import net.library.spring.dto.BookAuthorDTO;
import net.library.spring.entities.BookAuthor;
import net.library.spring.service.ServiceBookAuthor;
import net.library.spring.utils.converterDTO.impl.ConverterBookAuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceBookAuthorImpl extends ServiceBase<BookAuthorDTO, DAOBookAuthor, BookAuthor> implements ServiceBookAuthor {

    @Autowired
    public ServiceBookAuthorImpl(DAOBookAuthor dao, ConverterBookAuthorDTO converter) {
        super(dao, converter);
    }

}