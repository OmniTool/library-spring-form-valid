package net.library.spring.controller;

import net.library.spring.entities.Author;
import net.library.spring.entities.Book;
import net.library.spring.entities.BookAuthor;
import net.library.spring.service.ServiceAuthor;
import net.library.spring.service.ServiceBook;
import net.library.spring.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(MainController.AUTHOR_ROOT_URL)
public class AuthorsController {

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
    @Autowired private Validator<Author> validator;

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
        Author entity = new Author();
        entity.setFirstName(firstName);
        entity.setMiddleName(middleName);
        entity.setSecondName(secondName);
        map.put(AUTHORS_LIST_ATTRIBUTE_NAME, serviceAuthor.searchEntityByName(entity));
        return AUTHORS_LIST_VIEW;
    }

    @RequestMapping(value = MainController.ADD_ACTION_URL, method = RequestMethod.GET)
    public String createAuthor(Map<String, Object> map) {
        Author entity = new Author();
        initAttributes(map, entity);
        return AUTHOR_ADD_VIEW;
    }
    @RequestMapping(value = MainController.ADD_ACTION_URL, method = RequestMethod.POST)
    public String createAuthor(@RequestParam(value= BOOKS_LIST_ATTRIBUTE_NAME, required=false) List<Integer> listBookIds,
                               @ModelAttribute(AUTHOR_ATTRIBUTE_NAME) @Valid Author author,
                             BindingResult result, Map<String, Object> map) {
        initEntityFromAttributes(author, new ArrayList<BookAuthor>(), listBookIds);
        if (!dataIsCorrect(author, result, map)) return AUTHOR_ADD_VIEW;
        serviceAuthor.create(author);
        return "redirect:" + MainController.AUTHOR_ROOT_URL + MainController.SHOW_ALL_ACTION_URL;
    }
    @RequestMapping(value = "/{id}")
    public String readAuthor(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put(AUTHOR_ATTRIBUTE_NAME, serviceAuthor.getEntityById(id));
        return AUTHOR_INFO_VIEW;
    }
    @RequestMapping(value = MainController.EDIT_ACTION_URL + "/{id}", method = RequestMethod.GET)
    public String updateAuthor(@PathVariable("id") Integer id, Map<String, Object> map) {
        initAttributes(map, serviceAuthor.getEntityById(id));
        return AUTHOR_EDIT_VIEW;
    }
    @RequestMapping(value = MainController.EDIT_ACTION_URL + "/{id}", method = RequestMethod.POST)
    public String updateAuthor(@RequestParam(value= BOOKS_LIST_ATTRIBUTE_NAME, required=false) List<Integer> listBookIds,
                               @ModelAttribute(AUTHOR_ATTRIBUTE_NAME) @Valid Author author,
                            BindingResult result, Map<String, Object> map) {
        Author oldAuthor = serviceAuthor.getEntityById(author.getId());
        initEntityFromAttributes(author, oldAuthor.getBooksList(), listBookIds);
        if (!dataIsCorrect(author, result, map)) return AUTHOR_EDIT_VIEW;
        serviceAuthor.update(author);
        return "redirect:" + MainController.AUTHOR_ROOT_URL + "/" + author.getId();
    }
    @RequestMapping(value = MainController.REMOVE_ACTION_URL + "/{id}", method = RequestMethod.GET)
    public String deleteAuthor(@PathVariable("id") Integer id) {
        Author author = serviceAuthor.getEntityById(id);
        author.getBooksList().clear();
        serviceAuthor.update(author);
        serviceAuthor.delete(id);
        return "redirect:" + MainController.AUTHOR_ROOT_URL + MainController.SHOW_ALL_ACTION_URL;
    }
    private void initAttributes(Map<String, Object> map, Author author) {
        validator.trim(author);
        map.put(AUTHOR_ATTRIBUTE_NAME, author);
        List<Book> books = new ArrayList<>();
        for (BookAuthor bookAuthor : author.getBooksList())
            books.add(bookAuthor.getBook());
        map.put(SELECTED_BOOKS_LIST_ATTRIBUTE_NAME, books);
        map.put(SOURCE_BOOKS_LIST_ATTRIBUTE_NAME, serviceBook.getAll());
    }
    private void initEntityFromAttributes(Author author, List<BookAuthor> booksList, List<Integer> listBookIds) {
        author.setBooksList(booksList);
        author.getBooksList().clear();
        if (listBookIds != null) for (int id : listBookIds) {
            author.getBooksList().add(new BookAuthor(serviceBook.getEntityById(id), author));
        }
    }
    private boolean dataIsCorrect(@ModelAttribute(AUTHOR_ATTRIBUTE_NAME) @Valid Author author, BindingResult result, Map<String, Object> map) {
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

