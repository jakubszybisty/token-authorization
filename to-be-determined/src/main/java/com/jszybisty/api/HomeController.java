package com.jszybisty.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    public static final String ALL = "This is available for everyone";
    public static final String AUTHORIZED  = "If you can see this, that means you are successfully authorized";
    public static final String SUPER_USER = "If you can seee this, you are definitely a super-user";

    @RequestMapping("/")
    public String home() {
        return ALL;
    }

    @RequestMapping("/normal")
    public String authorized() {
        return AUTHORIZED;
    }

    @RequestMapping("/super")
    public String superUser() {
        return SUPER_USER;
    }

}
