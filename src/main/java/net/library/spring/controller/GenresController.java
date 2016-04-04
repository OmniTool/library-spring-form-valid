package net.library.spring.controller;

import net.library.spring.entities.Book;
import net.library.spring.entities.Genre;
import net.library.spring.service.ServiceBook;
import net.library.spring.service.ServiceGenre;
import net.library.spring.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class GenresController {

    @Autowired private ServiceGenre serviceGenre;
    @Autowired private ServiceBook serviceBook;
    @Autowired private Validator<Genre> validator;

    @RequestMapping("/genres")
    public String listGenres(Map<String, Object> map) {
        map.put("list", serviceGenre.getAll());
        return "genrelist";
    }
    @RequestMapping(value = "/findgenrebyname", method = RequestMethod.POST)
    public String findGenre(@RequestParam("title") String title, Map<String, Object> map) {
        Genre entity = new Genre();
        entity.setTitle(title);
        map.put("list", serviceGenre.searchEntityByName(entity));
        return "genrelist";
    }

    @RequestMapping(value = "/addgenre", method = RequestMethod.GET)
    public String createGenre(Map<String, Object> map) {
        Genre entity = new Genre();
        initParameters(entity,map);
        return "addgenre";
    }
    @RequestMapping(value = "/addgenre", method = RequestMethod.POST)
    public String createGenre(@ModelAttribute("entity") @Valid Genre entity,
                             BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            initParameters(entity, map);
            return "addgenre";
        }
        if (validator.exists(entity)) {
            map.put("message", true);
            initParameters(entity, map);
            return "addgenre";
        }
        serviceGenre.create(entity);
        return "redirect:/genres";
    }
    @RequestMapping(value = "/findgenre/{genreId}")
    public String readGenre(@PathVariable("genreId") Integer id, Map<String, Object> map) {
        map.put("entity", serviceGenre.getEntityById(id));
        return "genreinfo";
    }
    @RequestMapping(value = "/editgenre/{genreId}", method = RequestMethod.GET)
    public String updateGenre(@PathVariable("genreId") Integer id, Map<String, Object> map) {
        initParameters(serviceGenre.getEntityById(id), map);
        return "editgenre";
    }
    @RequestMapping(value = "/editgenre/{genreId}", method = RequestMethod.POST)
    public String updateGenre(@ModelAttribute("entity") @Valid Genre entity,
                            BindingResult result, Map<String, Object> map) {
        if (result.hasErrors()) {
            initParameters(entity, map);
            return "editgenre";
        }
        boolean exists;
        Genre oldEntity = serviceGenre.getEntityById(entity.getId());
        validator.trim(oldEntity);
        if (entity.getTitle().equals(oldEntity.getTitle())) {
            exists = false;
        } else {
            exists = validator.exists(entity);
        }
        if (exists) {
            map.put("message", true);
            initParameters(entity, map);
            return "editgenre";
        }
        serviceGenre.update(entity);
        return "redirect:/findgenre/" + entity.getId();
    }
    @RequestMapping(value = "/removegenre/{genreId}", method = RequestMethod.GET)
    public String deleteGenre(@PathVariable("genreId") Integer id, Map<String, Object> map) {
        Genre entity = serviceGenre.getEntityById(id);
        List<Book> listBooks = serviceBook.searchBooksByGenre(entity);
        if (listBooks.size() != 0) {
            map.put("message", true);
            map.put("listBooks", listBooks);
            initParameters(entity, map);
            return "genreinfo";
        }
        serviceGenre.delete(id);
        return "redirect:/genres";
    }
    private void initParameters(@ModelAttribute("entity") Genre entity, Map<String, Object> map) {
        validator.trim(entity);
        map.put("entity", entity);
    }
}

