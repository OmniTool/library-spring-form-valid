package net.library.spring.dao.impl;

import net.library.spring.dao.DAOGenre;
import net.library.spring.entities.Genre;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class DAOGenreImpl extends DAOBase<Genre> implements DAOGenre {

    public DAOGenreImpl() {
        super(Genre.class);
    }

    @Override
    public List<Genre> searchEntityByName(Genre genre) {
        if (genre == null) return new ArrayList<>();
        List<Criterion> restrictions = new ArrayList<>();
        restrictions.add(Restrictions.like("title", "%" + genre.getTitle() + "%").ignoreCase());
        return super.searchEntityByCriteria(restrictions);
    }


}
