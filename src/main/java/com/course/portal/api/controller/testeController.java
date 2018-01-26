package com.course.portal.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testeController {

    @GetMapping("valor")
    public String getValor(){

        return "vamos la ";
    }
}
