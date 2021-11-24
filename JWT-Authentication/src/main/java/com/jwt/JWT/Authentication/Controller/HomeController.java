package com.jwt.JWT.Authentication.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/welcome")
    public String welcome(){
        String text="This is private page";
        text+=" this page is not allowed to authenticated user";
        return text;
    }
    @GetMapping("/getUser")
    public String getUser(){

        //return "{\"name\":\"Bhupendra\"}";
        return "Hello Bhupendra";
    }
}
