package net.library.spring.service.impl;

import net.library.spring.dao.DAO;
import net.library.spring.dto.BaseDTO;
import net.library.spring.entities.EntityBase;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class ServiceBase<T extends BaseDTO, D extends DAO, E extends EntityBase> {

    private D dao;
    private ConverterEntityDTO<E, T> converterDTO;

    public ServiceBase(D dao, ConverterEntityDTO converterDTO) {
        this.dao = dao;
        this.converterDTO = converterDTO;
    }

    public List<T> getAll() {
        List<T> listOfDTOs = new ArrayList<>();
        List<E> listOfEntities = dao.getAll();
        for (E entity : listOfEntities) listOfDTOs.add(converterDTO.packEntityToDTO(entity));
        return listOfDTOs;
    }

    public T getEntityById(Integer id) {
        E entity = (E) dao.getEntityById(id);
        return converterDTO.packEntityToDTO(entity);
    }

    public void update(T entityDTO) {
        dao.update(entityDTO.getEntity());
    }

    public void delete(Integer id) {
        E entity = (E) dao.getEntityById(id);
        dao.delete(entity);
    }

    public int create(T entityDTO) {
        return dao.create(entityDTO.getEntity());
    }

    public List<T> searchEntityByName(T entityDTO) {
        List<T> listOfDTOs = new ArrayList<>();
        List<E> listOfEntities = dao.searchEntityByName(entityDTO.getEntity());
        for (E entity : listOfEntities) listOfDTOs.add(converterDTO.packEntityToDTO(entity));
        return listOfDTOs;
    }

    protected D getDao() {
        return dao;
    }
}
