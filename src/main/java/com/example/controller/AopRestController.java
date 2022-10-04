package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Aop")
public class AopRestController {

    @RequestMapping("/getAop/{name}")
    public ResponseEntity<String> helloAop(@PathVariable(name = "name", required = false) String name){
        return ResponseEntity.ok("Hello World! Hello" + name);
    }
}
