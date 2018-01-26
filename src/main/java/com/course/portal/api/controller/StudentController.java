package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.repository.StudentRepository;
import com.course.portal.api.model.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(value = "/api/login")
public class StudentController {

   // StudentRepository studentRepository;

    @PostMapping(value = "/response")
    public ResponseEntity<Response<StudentDTO>> testeStudent(@RequestBody StudentDTO studentDTO){
        Response<StudentDTO> response = new Response<StudentDTO>();

        studentDTO.set_id(1L);
        response.setData(studentDTO);
        System.out.println("To aqui mano");
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/teste")
    public String teste(){
        System.out.println("To aqui olha eu ");
        return "deu certo";
    }

}
