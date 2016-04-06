package net.library.spring.service.impl;

import net.library.spring.dao.DAOAuthor;
import net.library.spring.dto.*;
import net.library.spring.entities.Author;
import net.library.spring.service.ServiceAuthor;
import net.library.spring.utils.converterDTO.impl.ConverterAuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceAuthorImpl extends ServiceBase<AuthorDTO, DAOAuthor, Author> implements ServiceAuthor {

    @Autowired
    public ServiceAuthorImpl(DAOAuthor dao, ConverterAuthorDTO converter) {
        super(dao, converter);
    }

//    @Override
//    public void delete(Integer id) {
//        Author author = getDao().getEntityById(id); //TODO повторить в ServiceBookImpl
//        author.getBooksList().clear();
//        getDao().update(author);
//        getDao().delete(author);
//    }
}
