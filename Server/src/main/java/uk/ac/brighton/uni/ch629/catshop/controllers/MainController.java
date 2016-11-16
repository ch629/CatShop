package uk.ac.brighton.uni.ch629.catshop.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/")
public class MainController { //TODO: Look into Security

    @PostMapping(value = "/subscribe")
    //NOTE: Client should probably send a packet back when it receives an update, so the server knows to keep communication between them
    public void subscribe(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
        //Replace payload with an object if I can. Would make life easier.
        //Request allows me to get the IP address that sent the request

        String ip = request.getRemoteHost();
        int requestPort = request.getRemotePort(); //Probably wont use this for the communication, or may do not sure. Maybe because of using Uni computers I should just let it be this.

    }
}