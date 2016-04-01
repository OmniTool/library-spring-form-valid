package net.library.spring.dao.impl;

import net.library.spring.dao.DAOAuthor;
import net.library.spring.entities.*;
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
            entities = currentSession().createQuery("FROM " + type.getSimpleName() +
                    " e WHERE upper(e.firstName) LIKE upper(:first_name) " +
                    "AND upper(e.secondName) LIKE upper(:second_name) " +
                    "AND upper(e.middleName) LIKE upper(:middle_name)")
                    .setParameter("first_name", "%" + entity.getFirstName() + "%")
                    .setParameter("second_name", "%" + entity.getSecondName()+ "%")
                    .setParameter("middle_name", "%" + entity.getMiddleName() + "%")
                    .list();
        return entities;
    }
}
