package net.library.spring.dao.impl;

import net.library.spring.dao.DAOBookAuthor;
import net.library.spring.entities.BookAuthor;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DAOBookAuthorImpl extends DAOBase<BookAuthor> implements DAOBookAuthor {

    public DAOBookAuthorImpl() {
        super(BookAuthor.class);
    }

    @Override
    public List<BookAuthor> searchEntityByName(BookAuthor bookAuthor) {
        if (bookAuthor == null) return new ArrayList<>();
        List<Criterion> restrictions = new ArrayList<>();
        restrictions.add(Restrictions.eq("author.id", bookAuthor.getAuthor().getId()));
        restrictions.add(Restrictions.eq("book.id", bookAuthor.getBook().getId()));
        return super.searchEntityByCriteria(restrictions);
    }
}