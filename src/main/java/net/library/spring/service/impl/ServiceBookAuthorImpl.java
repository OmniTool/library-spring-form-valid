package net.library.spring.service.impl;

import net.library.spring.dao.DAOBookAuthor;
import net.library.spring.dao.impl.DAOBookAuthorImpl;
import net.library.spring.entities.*;
import net.library.spring.service.ServiceBookAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServiceBookAuthorImpl extends ServiceBase<BookAuthor> implements ServiceBookAuthor {

    protected DAOBookAuthor dao;
    @Autowired
    public ServiceBookAuthorImpl(DAOBookAuthor dao) {
        super(dao);
        this.dao = dao;
    }

    public List<Book> searchBooksByAuthor(Author entity) {
        List<Book> books = new ArrayList<>();
        DAOBookAuthorImpl specialDAO = (DAOBookAuthorImpl) dao;
        for (Book e : specialDAO.searchBooksByAuthor(entity))
            books.add(e);
        return books;
    }
    public List<Author> searchAuthorsByBook(Book entity) {
        List<Author> books = new ArrayList<>();
        DAOBookAuthorImpl specialDAO = (DAOBookAuthorImpl) dao;
        for (Author e : specialDAO.searchAuthorsByBook(entity))
            books.add(e);
        return books;
    }
}