package net.library.spring.dao.impl;

import net.library.spring.dao.DAOAuthor;
import net.library.spring.entities.Author;
import org.hibernate.criterion.Criterion;
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
    public List<Author> searchEntityByName(Author author) {
        if (author == null) return new ArrayList<>();
        List<Criterion> restrictions = new ArrayList<>();
        restrictions.add(Restrictions.like("firstName", "%" + author.getFirstName() + "%").ignoreCase());
        restrictions.add(Restrictions.like("secondName", "%" + author.getSecondName() + "%").ignoreCase());
        restrictions.add(Restrictions.like("middleName", "%" + author.getMiddleName() + "%").ignoreCase());
        return super.searchEntityByCriteria(restrictions);
    }
}
