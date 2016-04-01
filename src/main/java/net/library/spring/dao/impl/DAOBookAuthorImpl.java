package net.library.spring.dao.impl;

import net.library.spring.dao.DAOBookAuthor;
import net.library.spring.entities.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            entities = currentSession().createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.author.id = :author_id AND e.book.id = :book_id")
                    .setParameter("author_id", entity.getAuthor().getId())
                    .setParameter("book_id", entity.getBook().getId())
                    .list();
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