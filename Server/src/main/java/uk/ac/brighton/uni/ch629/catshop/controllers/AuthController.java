package uk.ac.brighton.uni.ch629.catshop.controllers;

import com.fasterxml.jackson.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import uk.ac.brighton.uni.ch629.catshop.database.model.data.services.interfaces.AuthTokenService;

import java.util.stream.Collectors;

@RestController("auth")
public class AuthController {
    private final AuthTokenService authTokenService;

    @Autowired
    public AuthController(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @PostMapping(value = "/auth/add")
    public void addAuth(@RequestBody AuthPostWrapper authPost) { //TODO: @RequestBody
        System.out.println("Adding Auth");
        System.out.println(authPost.getAction());
//        throw new NotImplementedException();
    }

    @GetMapping(value = "/auth/add")
    public ModelAndView getAuth() {
        ModelAndView modelAndView = new ModelAndView("addauth");
        modelAndView.addObject("requests",
                authTokenService
                        .findAll()
                        .stream()
                        .collect(Collectors.toList()));
        return modelAndView;
    }
}

@JsonAutoDetect
class AuthPostWrapper {
    private String action;
    private String token;

    public AuthPostWrapper() {
    }

    @JsonCreator
    public AuthPostWrapper(@JsonProperty("action") String action,
                           @JsonProperty("token") String token) {
        this.action = action;
        this.token = token;
    }

    @JsonGetter("action")
    public String getAction() {
        return action;
    }

    @JsonSetter("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonGetter("token")
    public String getToken() {
        return token;
    }

    @JsonSetter("token")
    public void setToken(String token) {
        this.token = token;
    }
}