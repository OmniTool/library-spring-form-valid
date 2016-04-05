package net.library.spring.service;

import net.library.spring.dto.*;

import java.util.List;

public interface ServiceBook extends Service<BookDTO, Integer> {

    List<BookDTO> searchBooksByGenre(GenreDTO genre);

}
