package uk.ac.brighton.uni.ch629.catshop.spring.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    List<Greeting> greetings = new ArrayList<>();

    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
        greetings.add(greeting);
        return greeting;
    }

    @RequestMapping("/greetings")
    public List<Greeting> greetings() {
        return greetings;
    }
}