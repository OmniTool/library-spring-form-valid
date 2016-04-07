package net.library.spring.dao.impl;

import net.library.spring.dao.DAOBook;
import net.library.spring.entities.Book;
import net.library.spring.entities.Genre;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DAOBookImpl extends DAOBase<Book> implements DAOBook {

    public DAOBookImpl() {
        super(Book.class);
    }
    @Override
    
    public List<Book> searchEntityByName(Book book) {
        if (book == null) return new ArrayList<>();
        List<Criterion> restrictions = new ArrayList<>();
        restrictions.add(Restrictions.like("title", "%" + book.getTitle() + "%").ignoreCase());
        return super.searchEntityByCriteria(restrictions);
    }
    
    public List<Book> searchBooksByGenre(Genre genre) {
        if (genre == null) return new ArrayList<>();
        List<Criterion> restrictions = new ArrayList<>();
        restrictions.add(Restrictions.eq("genre.id", genre.getId()));
        return super.searchEntityByCriteria(restrictions);
    }

}
