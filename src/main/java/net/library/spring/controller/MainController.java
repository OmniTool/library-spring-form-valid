package net.library.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    public static final String HOMEPAGE_VIEW = "index";

    @RequestMapping({"/","/home","index"})
    public String home() {
        return HOMEPAGE_VIEW;
    }
}

