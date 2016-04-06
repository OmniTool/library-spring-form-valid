package net.library.spring.dao.impl;

import net.library.spring.dao.DAOBook;
import net.library.spring.entities.*;
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
    
    public List<Book> searchEntityByName(Book entity) {
        List<Book> entities = new ArrayList<>();
        if (entity == null) return entities;
        entities = currentSession().createCriteria(type)
                .add(Restrictions.like("title", "%" + entity.getTitle() + "%").ignoreCase())
                .list();
        return entities;
    }
    
    public List<Book> searchBooksByGenre(Genre entity) {
        List<Book> entities = new ArrayList<>();
        if (entity == null) return entities;
            entities = currentSession().createQuery("FROM " + type.getSimpleName() +
                    " e WHERE e.genre.id = :genre_id")
                    .setParameter("genre_id", entity.getId())
                    .list();
        return entities;
    }

}
