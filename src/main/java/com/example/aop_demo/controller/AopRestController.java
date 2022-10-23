package com.example.aop_demo.controller;

import com.example.aop_demo.annotations.LogParameters;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aopDemo")
public class AopRestController {

    @GetMapping({"/{name}/{surname}/{age}", "/"})
    @LogParameters(params = {"name", "age"})
    public ResponseEntity<String> helloAop(@PathVariable(name = "name", required = false) String name,
                                           @PathVariable(name = "surname", required = false) String surname,
                                           @PathVariable(name = "age", required = false) String age){
        return ResponseEntity.ok("Hello World! Hello " + name + surname + "!" + "Age : " + age);
    }
}
