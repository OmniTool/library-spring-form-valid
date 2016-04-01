package net.library.spring.dao;

import java.util.List;

public interface DAO<E, K> {

    List<E> getAll();
    E getEntityById(K id);
    void update(E entity);
    void delete(E entity);
    int create(E entity);
    List<E> searchEntityByName(E entity);

}
