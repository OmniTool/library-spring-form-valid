package net.library.spring.dao.impl;

import net.library.spring.entities.EntityBase;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class DAOBase<T extends EntityBase> {

    private Class<T> type;
    @Autowired
    private SessionFactory sessionFactory;

    public DAOBase(Class<T> type) {
        this.type = type;
    }
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected List<T> searchEntityByCriteria(List<Criterion> restrictions) {
        List<T> entities = new ArrayList<>();
        if (restrictions == null) return entities;
        Criteria criteria = currentSession().createCriteria(type)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        for (Criterion restriction : restrictions) criteria.add(restriction);
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
        return (T) currentSession().get(type, id);
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
