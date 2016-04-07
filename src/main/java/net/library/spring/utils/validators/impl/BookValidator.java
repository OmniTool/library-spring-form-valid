package net.library.spring.utils.validators.impl;

import net.library.spring.dto.BookDTO;
import net.library.spring.entities.Book;
import net.library.spring.service.ServiceBook;
import net.library.spring.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookValidator extends ValidatorBase<BookDTO, Book> implements Validator<BookDTO> {

    @Autowired
    public BookValidator(ServiceBook serviceBook) { super(serviceBook); }
}
