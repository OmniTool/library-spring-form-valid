package net.library.spring.controller;

import net.library.spring.entities.*;
import net.library.spring.service.*;
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
}

