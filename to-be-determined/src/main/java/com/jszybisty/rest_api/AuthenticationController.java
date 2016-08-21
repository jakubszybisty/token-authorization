package com.jszybisty.rest_api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class AuthenticationController extends ApiController {

    @RequestMapping(value = AUTHENTICATE_URL,  method = RequestMethod.POST)
    public String authenticate() {
        return "authentication";
    }
}
