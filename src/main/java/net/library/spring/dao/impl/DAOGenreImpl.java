package net.library.spring.dao.impl;

import net.library.spring.dao.DAOGenre;
import net.library.spring.entities.*;
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
    public List<Genre> searchEntityByName(Genre entity) {
        List<Genre> entities = new ArrayList<>();
        if (entity == null) return entities;
            entities = currentSession().createQuery("FROM " + type.getSimpleName() +
                    " e WHERE upper(e.title) LIKE upper(:title)")
                    .setParameter("title", "%" + entity.getTitle() + "%")
                    .list();
        return entities;
    }


}
