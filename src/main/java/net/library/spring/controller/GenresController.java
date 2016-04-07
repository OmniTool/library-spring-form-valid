package net.library.spring.controller;

import net.library.spring.dto.*;
import net.library.spring.service.ServiceBook;
import net.library.spring.service.ServiceGenre;
import net.library.spring.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(GenresController.GENRE_ROOT_URL)
public class GenresController {

    public static final String GENRE_ROOT_URL = "/genre";
    private static final String GENRE_INFO_VIEW = "genreinfo";
    private static final String GENRE_EDIT_VIEW = "editgenre";
    private static final String GENRE_ADD_VIEW = "addgenre";
    private static final String GENRE_LIST_VIEW = "genrelist";
    private static final String TITLE_ATTRIBUTE_NAME = "title";
    private static final String GENRE_ATTRIBUTE_NAME = "entity";
    private static final String GENRES_LIST_ATTRIBUTE_NAME = "list";
    private static final String WARNING_FOR_EXISTING_GENRE_ATTRIBUTE_NAME = "message";
    private static final String RELATED_BOOKS_LIST_ATTRIBUTE_NAME = "listBooks";
    @Autowired private ServiceGenre serviceGenre;
    @Autowired private ServiceBook serviceBook;
    @Autowired private Validator<GenreDTO> validator;

    @RequestMapping(MainController.SHOW_ALL_ACTION_URL)
    public String listGenres(Map<String, Object> map) {
        map.put(GENRES_LIST_ATTRIBUTE_NAME, serviceGenre.getAll());
        return GENRE_LIST_VIEW;
    }
    @RequestMapping(value = MainController.SEARCH_ACTION_URL, method = RequestMethod.POST)
    public String findGenre(@RequestParam(TITLE_ATTRIBUTE_NAME) String title, Map<String, Object> map) {
        GenreDTO genre = new GenreDTO();
        genre.setTitle(title);
        map.put(GENRES_LIST_ATTRIBUTE_NAME, serviceGenre.searchEntityByName(genre));
        return GENRE_LIST_VIEW;
    }

    @RequestMapping(value = MainController.ADD_ACTION_URL, method = RequestMethod.GET)
    public String createGenre(Map<String, Object> map) {
        GenreDTO genre = new GenreDTO();
        initAttributes(genre, map);
        return GENRE_ADD_VIEW;
    }
    @RequestMapping(value = MainController.ADD_ACTION_URL, method = RequestMethod.POST)
    public String createGenre(@ModelAttribute(GENRE_ATTRIBUTE_NAME) @Valid GenreDTO genre,
                             BindingResult result, Map<String, Object> map) {
        if (!dataIsCorrect(genre, result, map)) return GENRE_ADD_VIEW;
        serviceGenre.create(genre);
        return "redirect:" + GenresController.GENRE_ROOT_URL + MainController.SHOW_ALL_ACTION_URL;
    }
    @RequestMapping(value = "/{id}")
    public String readGenre(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put(GENRE_ATTRIBUTE_NAME, serviceGenre.getEntityById(id));
        return GENRE_INFO_VIEW;
    }
    @RequestMapping(value = MainController.EDIT_ACTION_URL + "/{id}", method = RequestMethod.GET)
    public String updateGenre(@PathVariable("id") Integer id, Map<String, Object> map) {
        initAttributes(serviceGenre.getEntityById(id), map);
        return GENRE_EDIT_VIEW;
    }
    @RequestMapping(value = MainController.EDIT_ACTION_URL + "/{id}", method = RequestMethod.POST)
    public String updateGenre(@ModelAttribute(GENRE_ATTRIBUTE_NAME) @Valid GenreDTO genre,
                            BindingResult result, Map<String, Object> map) {
        if (!dataIsCorrect(genre, result, map)) return GENRE_EDIT_VIEW;
        serviceGenre.update(genre);
        return "redirect:" + GenresController.GENRE_ROOT_URL + "/" + genre.getId();
    }
    @RequestMapping(value = MainController.REMOVE_ACTION_URL + "/{id}", method = RequestMethod.GET)
    public String deleteGenre(@PathVariable("id") Integer id, Map<String, Object> map) {
        GenreDTO genre = serviceGenre.getEntityById(id);
        List<BookDTO> listBooks = serviceBook.searchBooksByGenre(genre);
        if (listBooks.size() != 0) {
            map.put(WARNING_FOR_EXISTING_GENRE_ATTRIBUTE_NAME, true);
            map.put(RELATED_BOOKS_LIST_ATTRIBUTE_NAME, listBooks);
            initAttributes(genre, map);
            return GENRE_INFO_VIEW;
        }
        serviceGenre.delete(id);
        return "redirect:" + GenresController.GENRE_ROOT_URL + MainController.SHOW_ALL_ACTION_URL;
    }
    private void initAttributes(@ModelAttribute(GENRE_ATTRIBUTE_NAME) GenreDTO genre, Map<String, Object> map) {
        genre = genre.trim();
        map.put(GENRE_ATTRIBUTE_NAME, genre);
    }
    private boolean dataIsCorrect(@ModelAttribute(GENRE_ATTRIBUTE_NAME) @Valid GenreDTO genre, BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            initAttributes(genre, map);
            return false;
        }
        if (validator.exists(genre)) {
            map.put(WARNING_FOR_EXISTING_GENRE_ATTRIBUTE_NAME, true);
            initAttributes(genre, map);
            return false;
        }
        return true;
    }
}

