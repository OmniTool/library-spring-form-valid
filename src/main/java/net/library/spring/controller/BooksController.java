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
public class BooksController {

    @Autowired private ServiceBook serviceBook;
    @Autowired private ServiceAuthor serviceAuthor;
    @Autowired private ServiceGenre serviceGenre;
    @Autowired private Validator<Book> validator;

    @RequestMapping("/books")
    public String listBooks(Map<String, Object> map) {
        map.put("list", serviceBook.getAll());
        return "booklist";
    }
    @RequestMapping(value = "/findbookbyname", method = RequestMethod.POST)
    public String findBook(@RequestParam("title") String title, Map<String, Object> map) {
        Book entity = new Book();
        entity.setTitle(title);
        map.put("list", serviceBook.searchEntityByName(entity));
        return "booklist";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String createBook(Map<String, Object> map) {
        Book entity = new Book();
        initParameters(map, entity);
        return "addbook";
    }
    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String createBook(@RequestParam("genereId") int genereId,
                             @RequestParam(value="listAuthor", required=false) List<Integer> listAuthor,
                            @ModelAttribute("entity") @Valid Book entity,
                             BindingResult result, Map<String, Object> map) {
        Genre genre = serviceGenre.getEntityById(genereId);
        entity.setGenre(genre);
        entity.setAuthorsList(new ArrayList<BookAuthor>());
        if (listAuthor != null) for (int id : listAuthor) {
            entity.getAuthorsList().add(new BookAuthor(entity, serviceAuthor.getEntityById(id)));
        }
        if (result.hasErrors()) {
            initParameters(map, entity);
            return "addbook";
        }
        if (validator.exists(entity)) {
            map.put("message", true);
            initParameters(map, entity);
            return "addbook";
        }
        serviceBook.create(entity);
        return "redirect:/books";
    }
    @RequestMapping(value = "/findbook/{bookId}")
    public String readBook(@PathVariable("bookId") Integer id, Map<String, Object> map) {
        map.put("entity", serviceBook.getEntityById(id));
        return "bookinfo";
    }
    @RequestMapping(value = "/editbook/{bookId}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("bookId") Integer id, Map<String, Object> map) {
        initParameters(map, serviceBook.getEntityById(id));
        return "editbook";
    }
    @RequestMapping(value = "/editbook/{bookId}", method = RequestMethod.POST)
    public String updateBook(@RequestParam("genereId") int genereId,
                             @RequestParam(value="listAuthor", required=false) List<Integer> listAuthor,
                             @ModelAttribute("entity") @Valid Book entity,
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
            return "editbook";
        }
        if (exists) {
            map.put("message", true);
            initParameters(map, entity);
            return "editbook";
        }
        serviceBook.update(entity);
        return "redirect:/findbook/" + entity.getId();
    }
    @RequestMapping(value = "/removebook/{bookId}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("bookId") Integer id) {
        Book entity = serviceBook.getEntityById(id);
        entity.getAuthorsList().clear();
        serviceBook.update(entity);
        serviceBook.delete(id);
        return "redirect:/books";
    }
    private void initParameters(Map<String, Object> map, Book entity) {
        validator.trim(entity);
        map.put("entity", entity);
        List<Author> authors = new ArrayList<>();
        for (BookAuthor bookAuthor : entity.getAuthorsList())
            authors.add(bookAuthor.getAuthor());
        map.put("currentListAuthor", authors);
        map.put("sourceListAuthor", serviceAuthor.getAll());
        map.put("sourceListGenre", serviceGenre.getAll());
    }
}

