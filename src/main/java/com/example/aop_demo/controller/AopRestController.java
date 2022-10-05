package com.example.aop_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AopRestController {

    @GetMapping({"/aopDemo/{name}", "/"})
    public ResponseEntity<String> helloAop(@PathVariable(name = "name", required = false) String name){
        return ResponseEntity.ok("Hello World! Hello " + name + "!");
    }
}
