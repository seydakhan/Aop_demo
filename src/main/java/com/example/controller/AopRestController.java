package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopRestController {

    @GetMapping("/Aop/{name}")
    public String helloAop(@PathVariable String name){
        return "Hello World! Hello" + name;
    }
}
