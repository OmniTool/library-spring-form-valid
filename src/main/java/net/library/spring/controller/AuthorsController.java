package net.library.spring.controller;

import net.library.spring.entities.*;
import net.library.spring.service.*;
import net.library.spring.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AuthorsController {

    @Autowired
    private ServiceAuthor serviceAuthor;
    @Autowired
    private ServiceBook serviceBook;
    @Autowired
    private Validator<Author> validator;

    @RequestMapping("/authors")
    public String listAuthors(Map<String, Object> map) {
        map.put("list", serviceAuthor.getAll());
        return "authorlist";
    }
    @RequestMapping(value = "/addauthor", method = RequestMethod.GET)
    public String createAuthor(Map<String, Object> map) {
        Author entity = new Author();
        initParameters(map, entity);
        return "addauthor";
    }
    @RequestMapping(value = "/addauthor", method = RequestMethod.POST)
    public String createAuthor(@ModelAttribute("entity") Author entity,
                               @RequestParam(value="listBook", required=false) List<Integer> listBook,
                             BindingResult result, Map<String, Object> map) {
        entity.setBooksList(new ArrayList<BookAuthor>());
        if (listBook != null)for (int id : listBook) {
            entity.getBooksList().add(new BookAuthor(serviceBook.getEntityById(id), entity));
        }
        if (validator.exists(entity)) {
            map.put("message", true);
            initParameters(map, entity);
            return "addauthor";
        }
        serviceAuthor.create(entity);
        return "redirect:/authors";
    }
    @RequestMapping(value = "/findauthor/{authorId}")
    public String readAuthor(@PathVariable("authorId") Integer id, Map<String, Object> map) {
        map.put("entity", serviceAuthor.getEntityById(id));
        return "authorinfo";
    }
    @RequestMapping(value = "/editauthor/{authorId}", method = RequestMethod.GET)
    public String updateAuthor(@PathVariable("authorId") Integer id, Map<String, Object> map) {
        initParameters(map, serviceAuthor.getEntityById(id));
        return "editauthor";
    }
    @RequestMapping(value = "/editauthor/{authorId}", method = RequestMethod.POST)
    public String updateAuthor(@ModelAttribute("entity") Author entity,
                               @RequestParam(value="listBook", required=false) List<Integer> listBook,
                            BindingResult result, Map<String, Object> map) {
        boolean exists;
        Author oldEntity = serviceAuthor.getEntityById(entity.getId());
        validator.trim(oldEntity);
        entity.setBooksList(oldEntity.getBooksList());
        entity.getBooksList().clear();
        if (listBook != null) for (int id : listBook) {
            entity.getBooksList().add(new BookAuthor(serviceBook.getEntityById(id), entity));
        }
        if (entity.getFirstName().equals(oldEntity.getFirstName())
                && entity.getMiddleName().equals(oldEntity.getMiddleName())
                && entity.getSecondName().equals(oldEntity.getSecondName())
                && entity.getBirthYear().equals(oldEntity.getBirthYear())) {
            exists = false;
        } else {
            exists = validator.exists(entity);
        }
        if (exists) {
            map.put("message", true);
            initParameters(map, entity);
            return "editauthor";
        }
        serviceAuthor.update(entity);
        return "redirect:/findauthor/" + entity.getId();
    }
    @RequestMapping(value = "/removeauthor/{authorId}", method = RequestMethod.GET)
    public String deleteAuthor(@PathVariable("authorId") Integer id) {
        Author entity = serviceAuthor.getEntityById(id);
        entity.getBooksList().clear();
        serviceAuthor.update(entity);
        serviceAuthor.delete(id);
        return "redirect:/authors";
    }
    @RequestMapping(value = "/findauthorbyname", method = RequestMethod.POST)
    public String findAuthor(@RequestParam("title") String firstName,
                             @RequestParam("title") String middleName,
                             @RequestParam("title") String secondName,
                             Map<String, Object> map) {
        Author entity = new Author();
        entity.setFirstName(firstName);
        entity.setMiddleName(middleName);
        entity.setSecondName(secondName);
        map.put("list", serviceAuthor.searchEntityByName(entity));
        return "authorlist";
    }
    private void initParameters(Map<String, Object> map, Author entity) {
        validator.trim(entity);
        map.put("entity", entity);
        List<Book> books = new ArrayList<>();
        for (BookAuthor bookAuthor : entity.getBooksList())
            books.add(bookAuthor.getBook());
        map.put("currentListBook", books);
        map.put("sourceListBook", serviceBook.getAll());
    }
}

