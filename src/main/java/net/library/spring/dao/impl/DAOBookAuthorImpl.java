package net.library.spring.dao.impl;

import net.library.spring.dao.DAOBookAuthor;
import net.library.spring.entities.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class DAOBookAuthorImpl extends DAOBase<BookAuthor> implements DAOBookAuthor {

    public DAOBookAuthorImpl() {
        super(BookAuthor.class);
    }
    @Override
    
    public List<BookAuthor> searchEntityByName(BookAuthor entity) {
        List<BookAuthor> entities = new ArrayList<>();
        if (entity == null) return entities;
        Map<String, String> restrictions = new HashMap<>();
        restrictions.put("author.id", String.valueOf(entity.getAuthor().getId()));
        restrictions.put("book.id", String.valueOf(entity.getBook().getId()));
        entities = super.searchEntityByName(restrictions);
        return entities;
    }
    
    public List<Book> searchBooksByAuthor(Author entity) {
        List<BookAuthor> entities = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        if (entity == null) return books;
            entities = currentSession().createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.author.id = :author_id")
                    .setParameter("author_id", entity.getId())
                    .list();
        for (BookAuthor bookAuthor : entities) {
            books.add(bookAuthor.getBook());
        }
        return books;
    }
    
    public List<Author> searchAuthorsByBook(Book entity) {
        List<BookAuthor> entities = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        if (entity == null) return authors;
            entities = currentSession().createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.book.id = :book_id")
                    .setParameter("book_id", entity.getId())
                    .list();
        for (BookAuthor bookAuthor : entities) {
            authors.add(bookAuthor.getAuthor());
        }
        return authors;
    }
}