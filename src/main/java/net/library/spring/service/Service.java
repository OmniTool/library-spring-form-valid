package net.library.spring.service;

import net.library.spring.dto.BaseDTO;
import net.library.spring.entities.EntityBase;
import net.library.spring.utils.converterDTO.ConverterEntityDTO;

import java.util.List;

public interface Service<D extends BaseDTO, K, B extends EntityBase> {

    List<D> getAll();

    D getEntityById(K id);

    void update(D entity);

    void delete(K id);

    int create(D entity);

    List<D> searchEntityByName(D entity);

    ConverterEntityDTO<B, D> getConverterDTO();
}
