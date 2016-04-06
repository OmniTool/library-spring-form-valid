package net.library.spring.dao.impl;

import net.library.spring.dao.DAOGenre;
import net.library.spring.entities.Genre;
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
public class DAOGenreImpl extends DAOBase<Genre> implements DAOGenre {

    public DAOGenreImpl() {
        super(Genre.class);
    }
    @Override
    public List<Genre> searchEntityByName(Genre entity) {
        List<Genre> entities = new ArrayList<>();
        if (entity == null) return entities;
        Map<String, String> restrictions = new HashMap<>();
        restrictions.put("title", "%" + entity.getTitle() + "%");
        entities = super.searchEntityByName(restrictions);
        return entities;
    }


}
