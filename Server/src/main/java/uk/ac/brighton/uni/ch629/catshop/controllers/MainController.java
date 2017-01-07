package uk.ac.brighton.uni.ch629.catshop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.brighton.uni.ch629.catshop.Application;

@RestController("/")
public class MainController {
    @GetMapping(value = "/subscribe/port")
    public int getSubscriptionPort() {
        return Application.getSubscriptionPort();
    }
}