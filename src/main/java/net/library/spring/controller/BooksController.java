package net.library.spring.controller;

import net.library.spring.entities.Author;
import net.library.spring.entities.Book;
import net.library.spring.entities.BookAuthor;
import net.library.spring.entities.Genre;
import net.library.spring.service.ServiceAuthor;
import net.library.spring.service.ServiceBook;
import net.library.spring.service.ServiceGenre;
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
@RequestMapping(MainController.BOOK_ROOT_URL)
public class BooksController {

    private static final String BOOK_EDIT_VIEW = "editbook";
    private static final String BOOK_INFO_VIEW = "bookinfo";
    private static final String BOOK_ADD_VIEW = "addbook";
    private static final String BOOKS_LIST_VIEW = "booklist";
    private static final String TITLE_ATTRIBUTE_NAME = "title";
    private static final String BOOK_ATTRIBUTE_NAME = "entity";
    private static final String BOOKS_LIST_ATTRIBUTE_NAME = "list";
    private static final String WARNING_FOR_EXISTING_BOOK_ATTRIBUTE_NAME = "message";
    private static final String SOURCE_AUTHORS_LIST_ATTRIBUTE_NAME = "sourceListAuthor";
    private static final String SOURCE_GENRES_LIST_ATTRIBUTE_NAME = "sourceListGenre";
    private static final String SELECTED_AUTHORS_LIST_ATTRIBUTE_NAME = "currentListAuthor";
    public static final String AUTHORS_LIST_ATTRIBUTE_NAME = "listAuthor";
    public static final String GENERE_ID_ATTRIBUTE_NAME = "genereId";
    @Autowired private ServiceBook serviceBook;
    @Autowired private ServiceAuthor serviceAuthor;
    @Autowired private ServiceGenre serviceGenre;
    @Autowired private Validator<Book> validator;

    @RequestMapping(MainController.SHOW_ALL_ACTION_URL)
    public String listBooks(Map<String, Object> map) {
        map.put(BOOKS_LIST_ATTRIBUTE_NAME, serviceBook.getAll());
        return BOOKS_LIST_VIEW;
    }
    @RequestMapping(value = MainController.SEARCH_ACTION_URL, method = RequestMethod.POST)
    public String findBook(@RequestParam(TITLE_ATTRIBUTE_NAME) String title, Map<String, Object> map) {
        Book book = new Book();
        book.setTitle(title);
        map.put(BOOKS_LIST_ATTRIBUTE_NAME, serviceBook.searchEntityByName(book));
        return BOOKS_LIST_VIEW;
    }

    @RequestMapping(value = MainController.ADD_ACTION_URL, method = RequestMethod.GET)
    public String createBook(Map<String, Object> map) {
        Book book = new Book();
        initAttributes(map, book);
        return BOOK_ADD_VIEW;
    }
    @RequestMapping(value = MainController.ADD_ACTION_URL, method = RequestMethod.POST)
    public String createBook(@RequestParam(GENERE_ID_ATTRIBUTE_NAME) int genereId,
                             @RequestParam(value= AUTHORS_LIST_ATTRIBUTE_NAME, required=false) List<Integer> listAuthorIds,
                            @ModelAttribute(BOOK_ATTRIBUTE_NAME) @Valid Book book,
                             BindingResult result, Map<String, Object> map) {
        initEntityFromAttributes(book, new ArrayList<BookAuthor>(), listAuthorIds, genereId);
        if (!dataIsCorrect(book, result, map)) return BOOK_ADD_VIEW;
        serviceBook.create(book);
        return "redirect:" + MainController.BOOK_ROOT_URL + MainController.SHOW_ALL_ACTION_URL;
    }
    @RequestMapping(value = "/{id}")
    public String readBook(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put(BOOK_ATTRIBUTE_NAME, serviceBook.getEntityById(id));
        return BOOK_INFO_VIEW;
    }
    @RequestMapping(value = MainController.EDIT_ACTION_URL + "/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("id") Integer id, Map<String, Object> map) {
        initAttributes(map, serviceBook.getEntityById(id));
        return BOOK_EDIT_VIEW;
    }
    @RequestMapping(value = MainController.EDIT_ACTION_URL + "/{id}", method = RequestMethod.POST)
    public String updateBook(@RequestParam(GENERE_ID_ATTRIBUTE_NAME) int genereId,
                             @RequestParam(value= AUTHORS_LIST_ATTRIBUTE_NAME, required=false) List<Integer> listAuthorIds,
                             @ModelAttribute(BOOK_ATTRIBUTE_NAME) @Valid Book book,
                            BindingResult result, Map<String, Object> map) {
        Book oldBook = serviceBook.getEntityById(book.getId());
        initEntityFromAttributes(book, oldBook.getAuthorsList(), listAuthorIds, genereId);
        if (!dataIsCorrect(book, result, map)) return BOOK_EDIT_VIEW;
        serviceBook.update(book);
        return "redirect:" + MainController.BOOK_ROOT_URL + "/" + book.getId();
    }
    @RequestMapping(value = MainController.REMOVE_ACTION_URL + "/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Integer id) {
        Book book = serviceBook.getEntityById(id);
        book.getAuthorsList().clear();
        serviceBook.update(book);
        serviceBook.delete(id);
        return "redirect:" + MainController.BOOK_ROOT_URL + MainController.SHOW_ALL_ACTION_URL;
    }
    private void initAttributes(Map<String, Object> map, Book book) {
        validator.trim(book);
        map.put(BOOK_ATTRIBUTE_NAME, book);
        List<Author> authors = new ArrayList<>();
        for (BookAuthor bookAuthor : book.getAuthorsList())
            authors.add(bookAuthor.getAuthor());
        map.put(SELECTED_AUTHORS_LIST_ATTRIBUTE_NAME, authors);
        map.put(SOURCE_AUTHORS_LIST_ATTRIBUTE_NAME, serviceAuthor.getAll());
        map.put(SOURCE_GENRES_LIST_ATTRIBUTE_NAME, serviceGenre.getAll());
    }
    private void initEntityFromAttributes(Book book, List<BookAuthor> authorsList, List<Integer> listAuthorIds, int genereId) {
        Genre genre = serviceGenre.getEntityById(genereId);
        book.setGenre(genre);
        book.setAuthorsList(authorsList);
        book.getAuthorsList().clear();
        if (listAuthorIds != null) for (int id : listAuthorIds) {
            book.getAuthorsList().add(new BookAuthor(book, serviceAuthor.getEntityById(id)));
        }
    }
    private boolean dataIsCorrect(@ModelAttribute(BOOK_ATTRIBUTE_NAME) @Valid Book book, BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            initAttributes(map, book);
            return false;
        }
        if (validator.exists(book)) {
            map.put(WARNING_FOR_EXISTING_BOOK_ATTRIBUTE_NAME, true);
            initAttributes(map, book);
            return false;
        }
        return true;
    }
}

