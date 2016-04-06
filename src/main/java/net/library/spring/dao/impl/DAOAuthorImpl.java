package net.library.spring.dao.impl;

import net.library.spring.dao.DAOAuthor;
import net.library.spring.entities.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DAOAuthorImpl extends DAOBase<Author> implements DAOAuthor {

    public DAOAuthorImpl() {
        super(Author.class);
    }
    @Override
    public List<Author> searchEntityByName(Author entity) {
        List<Author> entities = new ArrayList<>();
        if (entity == null) return entities;
        entities = currentSession().createCriteria(type)
                .add(Restrictions.like("firstName", "%" + entity.getFirstName() + "%").ignoreCase())
                .add(Restrictions.like("secondName", "%" + entity.getSecondName() + "%").ignoreCase())
                .add(Restrictions.like("middleName", "%" + entity.getMiddleName() + "%").ignoreCase())
                .list();
        return entities;
    }

    @Override
    public void delete(Author entity) {
        entity.getBooksList().clear();
        update(entity);
        super.delete(entity);
    }
}
