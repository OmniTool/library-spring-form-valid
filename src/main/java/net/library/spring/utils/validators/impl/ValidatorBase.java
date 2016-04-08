package net.library.spring.utils.validators.impl;

import net.library.spring.dto.BaseDTO;
import net.library.spring.entities.EntityBase;
import net.library.spring.service.Service;

import java.util.List;

public class ValidatorBase<D extends BaseDTO, B extends EntityBase> {

    private Service<D, Integer, B> service;

    public ValidatorBase(Service<D, Integer, B> service) { this.service = service; }

    public boolean exists(D dto) {
        dto = (D) dto.trim();
        List<D> list = service.searchEntityByName(dto);
        for (D authorFound : list) {
            authorFound = (D) authorFound.trim();
            if (dto.isIdenticalExceptId(authorFound)) return true;
        }
        return false;
    }
}
