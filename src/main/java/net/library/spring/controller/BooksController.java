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
public class BooksController {

    @Autowired private ServiceBook serviceBook;

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
}

