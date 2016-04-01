package net.library.spring.service;

import java.util.List;

public interface Service<E, K> {

    List<E> getAll();
    E getEntityById(K id);
    void update(E entity);
    void delete(K id);
    int create(E entity);
    List<E> searchEntityByName(E entity);

}
