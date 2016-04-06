package net.library.spring.dao.impl;

import net.library.spring.entities.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
public class DAOBase<T extends EntityBase> {

    protected Class<T> type;
    @Autowired
    private SessionFactory sessionFactory;

    public DAOBase(Class<T> type) {
        this.type = type;
    }
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<T> searchEntityByCriteria(Map<String, String> restrictions) {
        List<T> entities = new ArrayList<>();
        if (restrictions == null) return entities;
        Criteria criteria = currentSession().createCriteria(type)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        for (Map.Entry<String, String> restriction : restrictions.entrySet()) criteria.add(Restrictions.like(restriction.getKey(), restriction.getValue()).ignoreCase());
        entities = criteria.list();
        return entities;
    }

    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        entities = currentSession().createCriteria(type)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        return entities;
    }
    
    public T getEntityById(Integer id) {
        if (id == null) return null;
        T entity = (T) currentSession().get(type, id);
        return entity;
    }
    
    public void update(T entity) {
            currentSession().update(entity);
    }

    public void delete(T entity) {
        currentSession().delete(entity);
    }
    
    public int create(T entity){
        return (Integer) currentSession().save(entity);
    }
}
