package net.library.spring.controller;

import net.library.spring.entities.*;
import net.library.spring.service.*;
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
public class AuthorsController {

    @Autowired private ServiceAuthor serviceAuthor;

    @RequestMapping("/author/list")
    public String listAuthors(Map<String, Object> map) {
        map.put("list", serviceAuthor.getAll());
        return "authorlist";
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
}

