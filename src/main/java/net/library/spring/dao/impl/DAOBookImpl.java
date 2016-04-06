package net.library.spring.dao.impl;

import net.library.spring.dao.DAOBook;
import net.library.spring.entities.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, String> restrictions = new HashMap<>();
        restrictions.put("title", "%" + entity.getTitle() + "%");
        entities = super.searchEntityByName(restrictions);
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
