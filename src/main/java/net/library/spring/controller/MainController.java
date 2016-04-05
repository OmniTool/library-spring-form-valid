package net.library.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private static final String HOMEPAGE_VIEW = "index";
    public static final String SHOW_ALL_ACTION_URL = "/list";
    public static final String ADD_ACTION_URL = "/add";
    public static final String EDIT_ACTION_URL = "/edit";
    public static final String REMOVE_ACTION_URL = "/remove";
    public static final String SEARCH_ACTION_URL = "/find";

    @RequestMapping({"/","/home","index"})
    public String home() {
        return HOMEPAGE_VIEW;
    }
}

