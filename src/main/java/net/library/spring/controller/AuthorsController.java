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
//@RequestMapping("/author")
public class AuthorsController {

    public static final String AUTHOR_ATTRIBUTE_NAME = "entity";
    public static final String AUTHOR_LIST_VIEW = "authorlist";
    private static final String AUTHOR_EDIT_VIEW = "editauthor";
    public static final String AUTHOR_ADD_VIEW = "addauthor";

    @Autowired private ServiceAuthor serviceAuthor;
    @Autowired private ServiceBook serviceBook;
    @Autowired private Validator<Author> validator;

    //@RequestMapping("/author/list")
    @RequestMapping("/authors")
    public String listAuthors(Map<String, Object> map) {
        map.put("list", serviceAuthor.getAll());
        return AUTHOR_LIST_VIEW;
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
        return AUTHOR_LIST_VIEW;
    }

    @RequestMapping(value = "/" + AUTHOR_ADD_VIEW, method = RequestMethod.GET)
    public String createAuthor(Map<String, Object> map) {
        Author entity = new Author();
        initParameters(map, entity);
        return AUTHOR_ADD_VIEW;
    }
    @RequestMapping(value = "/" + AUTHOR_ADD_VIEW, method = RequestMethod.POST)
    public String createAuthor(@RequestParam(value="listBook", required=false) List<Integer> listBook,
                               @ModelAttribute("entity") @Valid Author entity,
                             BindingResult result, Map<String, Object> map) {
        entity.setBooksList(new ArrayList<BookAuthor>());
        if (listBook != null)for (int id : listBook) {
            entity.getBooksList().add(new BookAuthor(serviceBook.getEntityById(id), entity));
        }
        if (result.hasErrors()) {
            initParameters(map, entity);
            return AUTHOR_ADD_VIEW;
        }
        if (validator.exists(entity)) {
            map.put("message", true);
            initParameters(map, entity);
            return AUTHOR_ADD_VIEW;
        }
        serviceAuthor.create(entity);
        return "redirect:/authors";
    }
    @RequestMapping(value = "/findauthor/{authorId}")
    public String readAuthor(@PathVariable("authorId") Integer id, Map<String, Object> map) {
        map.put(AUTHOR_ATTRIBUTE_NAME, serviceAuthor.getEntityById(id));
        return "authorinfo";
    }
    @RequestMapping(value = "/" + AUTHOR_EDIT_VIEW + "/{authorId}", method = RequestMethod.GET)
    public String updateAuthor(@PathVariable("authorId") Integer id, Map<String, Object> map) {
        initParameters(map, serviceAuthor.getEntityById(id));
        return AUTHOR_EDIT_VIEW;
    }
    @RequestMapping(value = "/" + AUTHOR_EDIT_VIEW + "/{authorId}", method = RequestMethod.POST)
    public String updateAuthor(@RequestParam(value="listBook", required=false) List<Integer> listBook,
                               @ModelAttribute("entity") @Valid Author entity,
                            BindingResult result, Map<String, Object> map) {
        boolean exists;
        Author oldEntity = serviceAuthor.getEntityById(entity.getId());
        validator.trim(oldEntity);
        entity.setBooksList(oldEntity.getBooksList());
        entity.getBooksList().clear();
        if (listBook != null) for (int id : listBook) {
            entity.getBooksList().add(new BookAuthor(serviceBook.getEntityById(id), entity));
        }
        //todo: вынести в отдельный метод
        if (entity.getFirstName().equals(oldEntity.getFirstName())
                && entity.getMiddleName().equals(oldEntity.getMiddleName())
                && entity.getSecondName().equals(oldEntity.getSecondName())
                && entity.getBirthYear().equals(oldEntity.getBirthYear())) {
            exists = false;
        } else {
            exists = validator.exists(entity);
        }
        if (result.hasErrors()) {
            initParameters(map, entity);
            return AUTHOR_EDIT_VIEW;
        }
        if (exists) {
            map.put("message", true);
            initParameters(map, entity);
            return AUTHOR_EDIT_VIEW;
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

