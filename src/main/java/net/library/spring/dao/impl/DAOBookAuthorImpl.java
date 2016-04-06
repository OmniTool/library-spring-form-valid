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
        Criteria criteria = currentSession().createCriteria(type)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .add(Restrictions.eq("author.id", entity.getAuthor().getId()))
                .add(Restrictions.eq("book.id", entity.getBook().getId()));
        entities = criteria.list();
        return entities;
    }

}