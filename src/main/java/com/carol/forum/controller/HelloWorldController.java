package com.carol.forum.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@ResponseBody
public class HelloWorldController {

    @GetMapping
    ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello World");
    }
}
