package net.library.spring.utils.converterDTO;

import net.library.spring.dto.BaseDTO;
import net.library.spring.entities.EntityBase;

public interface ConverterEntityDTO<E extends EntityBase, D extends BaseDTO> {

    D packEntityToDTO(E entity);

    E unpackEntityFromDTO(D dto);
}
