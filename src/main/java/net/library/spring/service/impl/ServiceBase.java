package net.library.spring.service.impl;

import net.library.spring.dao.DAO;
import net.library.spring.entities.EntityBase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ServiceBase<T extends EntityBase, D extends DAO> {

    private D dao;

    public ServiceBase(D dao) {
        this.dao = dao;
    }

    public List<T> getAll() {
        return dao.getAll();
    }

    public T getEntityById(Integer id) {
        return (T) dao.getEntityById(id);
    }

    public void update(T entity) {
        dao.update(entity);
    }

    public void delete(Integer id) {
        T entity = (T) dao.getEntityById(id);
        dao.delete(entity);
    }

    public int create(T entity) {
        return dao.create(entity);
    }

    public List<T> searchEntityByName(T entity) {
        return dao.searchEntityByName(entity);
    }

    public D getDao() {
        return dao;
    }
}
