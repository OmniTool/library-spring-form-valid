package net.library.spring.controller;

import net.library.spring.dto.*;
import net.library.spring.service.*;
import net.library.spring.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(AuthorsController.AUTHOR_ROOT_URL)
public class AuthorsController {

    public static final String AUTHOR_ROOT_URL = "/author";
    private static final String AUTHORS_LIST_VIEW = "authorlist";
    private static final String AUTHOR_EDIT_VIEW = "editauthor";
    private static final String AUTHOR_ADD_VIEW = "addauthor";
    private static final String AUTHOR_INFO_VIEW = "authorinfo";
    private static final String AUTHOR_ATTRIBUTE_NAME = "entity";
    private static final String AUTHORS_LIST_ATTRIBUTE_NAME = "list";
    private static final String WARNING_FOR_EXISTING_AUTHOR_ATTRIBUTE_NAME = "message";
    private static final String SELECTED_BOOKS_LIST_ATTRIBUTE_NAME = "currentListBook";
    private static final String SOURCE_BOOKS_LIST_ATTRIBUTE_NAME = "sourceListBook";
    private static final String BOOKS_LIST_ATTRIBUTE_NAME = "listBook";
    private static final String FIRST_NAME_ATTRIBUTE_NAME = "firstName";
    private static final String MIDDLE_NAME_ATTRIBUTE_NAME = "middleName";
    private static final String SECOND_NAME_ATTRIBUTE_NAME = "secondName";

    @Autowired private ServiceAuthor serviceAuthor;
    @Autowired private ServiceBook serviceBook;
    @Autowired private Validator<AuthorDTO> validator;

    @RequestMapping(MainController.SHOW_ALL_ACTION_URL)
    public String listAuthors(Map<String, Object> map) {
        map.put(AUTHORS_LIST_ATTRIBUTE_NAME, serviceAuthor.getAll());
        return AUTHORS_LIST_VIEW;
    }
    @RequestMapping(value = MainController.SEARCH_ACTION_URL, method = RequestMethod.POST)
    public String findAuthor(@RequestParam(FIRST_NAME_ATTRIBUTE_NAME) String firstName,
                             @RequestParam(MIDDLE_NAME_ATTRIBUTE_NAME) String middleName,
                             @RequestParam(SECOND_NAME_ATTRIBUTE_NAME) String secondName,
                             Map<String, Object> map) {
        AuthorDTO author = new AuthorDTO();
        author.setFirstName(firstName);
        author.setMiddleName(middleName);
        author.setSecondName(secondName);
        map.put(AUTHORS_LIST_ATTRIBUTE_NAME, serviceAuthor.searchEntityByName(author));
        return AUTHORS_LIST_VIEW;
    }

    @RequestMapping(value = MainController.ADD_ACTION_URL, method = RequestMethod.GET)
    public String createAuthor(Map<String, Object> map) {
        AuthorDTO author = new AuthorDTO();
        initAttributes(map, author);
        return AUTHOR_ADD_VIEW;
    }
    @RequestMapping(value = MainController.ADD_ACTION_URL, method = RequestMethod.POST)
    public String createAuthor(@RequestParam(value= BOOKS_LIST_ATTRIBUTE_NAME, required=false) List<Integer> listBookIds,
                               @ModelAttribute(AUTHOR_ATTRIBUTE_NAME) @Valid AuthorDTO author,
                             BindingResult result, Map<String, Object> map) {
        author = initEntityFromAttributes(author, listBookIds);
        if (!dataIsCorrect(author, result, map)) return AUTHOR_ADD_VIEW;
        serviceAuthor.create(author);
        return "redirect:" + AuthorsController.AUTHOR_ROOT_URL + MainController.SHOW_ALL_ACTION_URL;
    }
    @RequestMapping(value = "/{id}")
    public String readAuthor(@PathVariable("id") Integer id, Map<String, Object> map) {
        initAttributes(map, serviceAuthor.getEntityById(id));
        return AUTHOR_INFO_VIEW;
    }
    @RequestMapping(value = MainController.EDIT_ACTION_URL + "/{id}", method = RequestMethod.GET)
    public String updateAuthor(@PathVariable("id") Integer id, Map<String, Object> map) {
        initAttributes(map, serviceAuthor.getEntityById(id));
        return AUTHOR_EDIT_VIEW;
    }
    @RequestMapping(value = MainController.EDIT_ACTION_URL + "/{id}", method = RequestMethod.POST)
    public String updateAuthor(@RequestParam(value= BOOKS_LIST_ATTRIBUTE_NAME, required=false) List<Integer> listBookIds,
                               @ModelAttribute(AUTHOR_ATTRIBUTE_NAME) @Valid AuthorDTO author,
                            BindingResult result, Map<String, Object> map) {
        if (!dataIsCorrect(author, result, map)) return AUTHOR_EDIT_VIEW;
        author = initEntityFromAttributes(author, listBookIds);
        serviceAuthor.update(author);
        return "redirect:" + AuthorsController.AUTHOR_ROOT_URL + "/" + author.getId();
    }
    @RequestMapping(value = MainController.REMOVE_ACTION_URL + "/{id}", method = RequestMethod.GET)
    public String deleteAuthor(@PathVariable("id") Integer id) {
        AuthorDTO author = serviceAuthor.getEntityById(id);
        author.getBooksIdList().clear();
        serviceAuthor.update(author);
        serviceAuthor.delete(id);
        return "redirect:" + AuthorsController.AUTHOR_ROOT_URL + MainController.SHOW_ALL_ACTION_URL;
    }
    private void initAttributes(Map<String, Object> map, AuthorDTO author) {
        author = validator.trim(author);
        map.put(AUTHOR_ATTRIBUTE_NAME, author);
        List<BookDTO> books = new ArrayList<>();
        for (Integer bookId : author.getBooksIdList()) {
            books.add(serviceBook.getEntityById(bookId));
        }
        map.put(SELECTED_BOOKS_LIST_ATTRIBUTE_NAME, books);
        map.put(SOURCE_BOOKS_LIST_ATTRIBUTE_NAME, serviceBook.getAll());
    }
    private AuthorDTO initEntityFromAttributes(AuthorDTO author, List<Integer> booksIdList) {
        author = validator.trim(author);
        author.setBooksIdList(booksIdList == null ? new ArrayList<Integer>() : booksIdList);
        return author;
    }
    private boolean dataIsCorrect(@ModelAttribute(AUTHOR_ATTRIBUTE_NAME) @Valid AuthorDTO author, BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            initAttributes(map, author);
            return false;
        }
        if (validator.exists(author)) {
            map.put(WARNING_FOR_EXISTING_AUTHOR_ATTRIBUTE_NAME, true);
            initAttributes(map, author);
            return false;
        }
        return true;
    }


}

