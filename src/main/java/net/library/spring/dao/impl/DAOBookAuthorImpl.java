package net.library.spring.dao.impl;

import net.library.spring.dao.DAOBookAuthor;
import net.library.spring.entities.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
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
        List<Criterion> restrictions = new ArrayList<>();
        restrictions.add(Restrictions.eq("author.id", entity.getAuthor().getId()));
        restrictions.add(Restrictions.eq("book.id", entity.getBook().getId()));
        entities = super.searchEntityByCriteria(restrictions);
        return entities;
    }

}