package com.smartbee.spring.tutorial.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by galihlasahido on 10/5/14.
 */
@Controller
public class Login {
    @RequestMapping(value = "/login/static", method = RequestMethod.GET)
    public String staticBoard() {
        return "login/login.page";
    }

    @RequestMapping(value="/user-login", method=RequestMethod.GET)
    public String loginForm() {
        return "login/login.page";
    }

    @RequestMapping(value="/error-login", method=RequestMethod.GET)
    public String invalidLogin(Model model) {
        model.addAttribute("error", true);
        return "login/login.page";
    }

    @RequestMapping(value="/denied")
    public String deniedLogin(Model model) {
        model.addAttribute("error", true);
        return "login/login.denied";
    }

}
