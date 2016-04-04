package net.library.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private static final String HOMEPAGE_VIEW = "index";
    public static final String SHOW_ALL_ACTION_PATH = "list";
    public static final String ADD_ACTION_PATH = "add";
    public static final String EDIT_ACTION_PATH = "edit";
    public static final String REMOVE_ACTION_PATH = "remove";
    public static final String SEARCH_ACTION_PATH = "find";

    @RequestMapping({"/","/home","index"})
    public String home() {
        return HOMEPAGE_VIEW;
    }
}

