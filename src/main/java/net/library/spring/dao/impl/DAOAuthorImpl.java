package net.library.spring.dao.impl;

import net.library.spring.dao.DAOAuthor;
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
public class DAOAuthorImpl extends DAOBase<Author> implements DAOAuthor {

    public DAOAuthorImpl() {
        super(Author.class);
    }
    @Override
    public List<Author> searchEntityByName(Author entity) {
        List<Author> entities = new ArrayList<>();
        if (entity == null) return entities;
        Map<String, String> restrictions = new HashMap<>();
        restrictions.put("firstName", "%" + entity.getFirstName() + "%");
        restrictions.put("secondName", "%" + entity.getSecondName() + "%");
        restrictions.put("middleName", "%" + entity.getMiddleName() + "%");
        entities = super.searchEntityByCriteria(restrictions);
        return entities;
    }

    @Override
    public void delete(Author entity) {
        entity.getBooksList().clear();
        update(entity);
        super.delete(entity);
    }
}
