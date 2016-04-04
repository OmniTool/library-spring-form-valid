package net.library.spring.service.impl;

import net.library.spring.dao.DAOAuthor;
import net.library.spring.entities.*;
import net.library.spring.service.ServiceAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServiceAuthorImpl extends ServiceBase<Author, DAOAuthor> implements ServiceAuthor {

    @Autowired
    public ServiceAuthorImpl(DAOAuthor dao) {
        super(dao);
    }
}
