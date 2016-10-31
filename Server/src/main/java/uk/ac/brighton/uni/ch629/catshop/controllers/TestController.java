package uk.ac.brighton.uni.ch629.catshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController { //TODO: To get HTML files, need to just be a @Controller rather than @RestController. Also it can't find .mustache files only .html, but works with mustache fine.
    @GetMapping(value = "/html")
    public String getHTML(Model model) {
        model.addAttribute("name", "Testing");
        return "index";
    }
}
