package ru.pupov.webtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.pupov.webtask.service.BookService;

@Controller
public class MainController {

    @GetMapping({"/", "/library"})
    public String index() {
        return "index";
    }
}
