package com.course.portal.api.controller;

import com.course.portal.api.model.dao.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

   // StudentRepository studentRepository;

    @GetMapping("/student")
    public String testeStudent(){

        //studentRepository.save();
        return "deu certo mano";
    }

}
