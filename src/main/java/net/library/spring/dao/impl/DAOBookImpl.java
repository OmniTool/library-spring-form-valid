package net.library.spring.dao.impl;

import net.library.spring.dao.DAOBook;
import net.library.spring.entities.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
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
        if (entity == null) return entities; //TODO double
        List<Criterion> restrictions = new ArrayList<>();
        restrictions.add(Restrictions.like("title", "%" + entity.getTitle() + "%").ignoreCase());
        entities = super.searchEntityByCriteria(restrictions);
        return entities;
    }
    
    public List<Book> searchBooksByGenre(Genre entity) {
        List<Book> entities = new ArrayList<>();
        if (entity == null) return entities;
        List<Criterion> restrictions = new ArrayList<>();
        restrictions.add(Restrictions.eq("genre.id", entity.getId()));
        entities = super.searchEntityByCriteria(restrictions);
        return entities;
    }

}
