package net.library.spring.dao.impl;

import net.library.spring.entities.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class DAOBase<T extends EntityBase> {

    Class<T> type;
    @Autowired
    private SessionFactory sessionFactory;

    public DAOBase(Class<T> type) {
        this.type = type;
    }
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    
    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        entities = currentSession().createQuery("FROM " + type.getSimpleName()).list();
        return entities;
    }
    
    public T getEntityById(Integer id) {
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
