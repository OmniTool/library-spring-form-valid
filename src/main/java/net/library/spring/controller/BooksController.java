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
        Book book = new Book();
        book.setTitle(title);
        map.put(BOOKS_LIST_ATTRIBUTE_NAME, serviceBook.searchEntityByName(book));
        return BOOKS_LIST_VIEW;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createBook(Map<String, Object> map) {
        Book book = new Book();
        initParameters(map, book);
        return BOOK_ADD_VIEW;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String createBook(@RequestParam("genereId") int genereId,
                             @RequestParam(value="listAuthor", required=false) List<Integer> listAuthor,
                            @ModelAttribute(BOOK_ATTRIBUTE_NAME) @Valid Book book,
                             BindingResult result, Map<String, Object> map) {
        Genre genre = serviceGenre.getEntityById(genereId);
        book.setGenre(genre);
        book.setAuthorsList(new ArrayList<BookAuthor>());
        if (listAuthor != null) for (int id : listAuthor) {
            book.getAuthorsList().add(new BookAuthor(book, serviceAuthor.getEntityById(id)));
        }
        if (result.hasErrors()) {
            initParameters(map, book);
            return BOOK_ADD_VIEW;
        }
        if (validator.exists(book)) {
            map.put(WARNING_FOR_EXISTING_BOOK_ATTRIBUTE_NAME, true);
            initParameters(map, book);
            return BOOK_ADD_VIEW;
        }
        serviceBook.create(book);
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
                             @ModelAttribute(BOOK_ATTRIBUTE_NAME) @Valid Book book,
                            BindingResult result, Map<String, Object> map) {
        boolean exists;
        Book oldBook = serviceBook.getEntityById(book.getId());
        validator.trim(oldBook);
        Genre genre = serviceGenre.getEntityById(genereId);
        book.setGenre(genre);
        book.setAuthorsList(oldBook.getAuthorsList());
        book.getAuthorsList().clear();
        if (listAuthor != null) for (int id : listAuthor) {
            book.getAuthorsList().add(new BookAuthor(book, serviceAuthor.getEntityById(id)));
        }
        if (book.equals(oldBook) && book.getGenre().getId() == oldBook.getGenre().getId()) {
            exists = false;
        } else {
            exists = validator.exists(book);
        }
        if (result.hasErrors()) {
            initParameters(map, book);
            return BOOK_EDIT_VIEW;
        }
        if (exists) {
            map.put(WARNING_FOR_EXISTING_BOOK_ATTRIBUTE_NAME, true);
            initParameters(map, book);
            return BOOK_EDIT_VIEW;
        }
        serviceBook.update(book);
        return "redirect:/book/" + book.getId();
    }
    @RequestMapping(value = "/remove/{bookId}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("bookId") Integer id) {
        Book book = serviceBook.getEntityById(id);
        book.getAuthorsList().clear();
        serviceBook.update(book);
        serviceBook.delete(id);
        return "redirect:/book/list";
    }
    private void initParameters(Map<String, Object> map, Book book) {
        validator.trim(book);
        map.put(BOOK_ATTRIBUTE_NAME, book);
        List<Author> authors = new ArrayList<>();
        for (BookAuthor bookAuthor : book.getAuthorsList())
            authors.add(bookAuthor.getAuthor());
        map.put(SELECTED_AUTHORS_LIST_ATTRIBUTE_NAME, authors);
        map.put(SOURCE_AUTHORS_LIST_ATTRIBUTE_NAME, serviceAuthor.getAll());
        map.put(SOURCE_GENRES_LIST_ATTRIBUTE_NAME, serviceGenre.getAll());
    }
}

