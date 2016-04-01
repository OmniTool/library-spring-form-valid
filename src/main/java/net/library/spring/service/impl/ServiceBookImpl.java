package net.library.spring.service.impl;

import net.library.spring.dao.DAOBook;
import net.library.spring.entities.*;
import net.library.spring.service.ServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceBookImpl extends ServiceBase<Book> implements ServiceBook {

    protected DAOBook dao;
    @Autowired
    public ServiceBookImpl(DAOBook dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Book> searchBooksByGenre(Genre genre) {
        List<Book> entities = new ArrayList<>();
        for (Book book : dao.searchBooksByGenre(genre))
            entities.add(book);
        return entities;
    }
}
