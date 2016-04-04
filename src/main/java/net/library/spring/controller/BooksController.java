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
@RequestMapping("/book")
public class BooksController {

    private static final String BOOK_ATTRIBUTE_NAME = "entity";
    private static final String BOOKS_LIST_ATTRIBUTE_NAME = "list";
    private static final String WARNING_FOR_EXISTING_BOOK_ATTRIBUTE_NAME = "message";
    private static final String SOURCE_AUTHORS_LIST_ATTRIBUTE_NAME = "sourceListAuthor";
    private static final String SOURCE_GENRES_LIST_ATTRIBUTE_NAME = "sourceListGenre";
    private static final String SELECTED_AUTHORS_LIST_ATTRIBUTE_NAME = "currentListAuthor";
    private static final String BOOK_EDIT_VIEW = "editbook";
    private static final String BOOK_INFO_VIEW = "bookinfo";
    private static final String BOOK_ADD_VIEW = "addbook";
    private static final String BOOKS_LIST_VIEW = "booklist";
    private static final String TITLE_ATTRIBUTE_NAME = "title";
    @Autowired private ServiceBook serviceBook;
    @Autowired private ServiceAuthor serviceAuthor;
    @Autowired private ServiceGenre serviceGenre;
    @Autowired private Validator<Book> validator;

    @RequestMapping("/list")
    public String listBooks(Map<String, Object> map) {
        map.put(BOOKS_LIST_ATTRIBUTE_NAME, serviceBook.getAll());
        return BOOKS_LIST_VIEW;
    }
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public String findBook(@RequestParam(TITLE_ATTRIBUTE_NAME) String title, Map<String, Object> map) {
        Book entity = new Book();
        entity.setTitle(title);
        map.put(BOOKS_LIST_ATTRIBUTE_NAME, serviceBook.searchEntityByName(entity));
        return BOOKS_LIST_VIEW;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createBook(Map<String, Object> map) {
        Book entity = new Book();
        initParameters(map, entity);
        return BOOK_ADD_VIEW;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createBook(@RequestParam("genereId") int genereId,
                             @RequestParam(value="listAuthor", required=false) List<Integer> listAuthor,
                            @ModelAttribute(BOOK_ATTRIBUTE_NAME) @Valid Book entity,
                             BindingResult result, Map<String, Object> map) {
        Genre genre = serviceGenre.getEntityById(genereId);
        entity.setGenre(genre);
        entity.setAuthorsList(new ArrayList<BookAuthor>());
        if (listAuthor != null) for (int id : listAuthor) {
            entity.getAuthorsList().add(new BookAuthor(entity, serviceAuthor.getEntityById(id)));
        }
        if (result.hasErrors()) {
            initParameters(map, entity);
            return BOOK_ADD_VIEW;
        }
        if (validator.exists(entity)) {
            map.put(WARNING_FOR_EXISTING_BOOK_ATTRIBUTE_NAME, true);
            initParameters(map, entity);
            return BOOK_ADD_VIEW;
        }
        serviceBook.create(entity);
        return "redirect:/book/list";
    }
    @RequestMapping(value = "/{bookId}")
    public String readBook(@PathVariable("bookId") Integer id, Map<String, Object> map) {
        map.put(BOOK_ATTRIBUTE_NAME, serviceBook.getEntityById(id));
        return BOOK_INFO_VIEW;
    }
    @RequestMapping(value = "/edit/{bookId}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("bookId") Integer id, Map<String, Object> map) {
        initParameters(map, serviceBook.getEntityById(id));
        return BOOK_EDIT_VIEW;
    }
    @RequestMapping(value = "/edit/{bookId}", method = RequestMethod.POST)
    public String updateBook(@RequestParam("genereId") int genereId,
                             @RequestParam(value="listAuthor", required=false) List<Integer> listAuthor,
                             @ModelAttribute(BOOK_ATTRIBUTE_NAME) @Valid Book entity,
                            BindingResult result, Map<String, Object> map) {
        boolean exists;
        Book oldEntity = serviceBook.getEntityById(entity.getId());
        validator.trim(oldEntity);
        Genre genre = serviceGenre.getEntityById(genereId);
        entity.setGenre(genre);
        entity.setAuthorsList(oldEntity.getAuthorsList());
        entity.getAuthorsList().clear();
        if (listAuthor != null) for (int id : listAuthor) {
            entity.getAuthorsList().add(new BookAuthor(entity, serviceAuthor.getEntityById(id)));
        }
        if (entity.equals(oldEntity) && entity.getGenre().getId() == oldEntity.getGenre().getId()) {
            exists = false;
        } else {
            exists = validator.exists(entity);
        }
        if (result.hasErrors()) {
            initParameters(map, entity);
            return BOOK_EDIT_VIEW;
        }
        if (exists) {
            map.put(WARNING_FOR_EXISTING_BOOK_ATTRIBUTE_NAME, true);
            initParameters(map, entity);
            return BOOK_EDIT_VIEW;
        }
        serviceBook.update(entity);
        return "redirect:/book/" + entity.getId();
    }
    @RequestMapping(value = "/remove/{bookId}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("bookId") Integer id) {
        Book entity = serviceBook.getEntityById(id);
        entity.getAuthorsList().clear();
        serviceBook.update(entity);
        serviceBook.delete(id);
        return "redirect:/book/list";
    }
    private void initParameters(Map<String, Object> map, Book entity) {
        validator.trim(entity);
        map.put(BOOK_ATTRIBUTE_NAME, entity);
        List<Author> authors = new ArrayList<>();
        for (BookAuthor bookAuthor : entity.getAuthorsList())
            authors.add(bookAuthor.getAuthor());
        map.put(SELECTED_AUTHORS_LIST_ATTRIBUTE_NAME, authors);
        map.put(SOURCE_AUTHORS_LIST_ATTRIBUTE_NAME, serviceAuthor.getAll());
        map.put(SOURCE_GENRES_LIST_ATTRIBUTE_NAME, serviceGenre.getAll());
    }
}

