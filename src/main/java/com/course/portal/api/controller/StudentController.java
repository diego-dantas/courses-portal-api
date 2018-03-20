package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.StudentEntity;
import com.course.portal.api.model.dao.repository.StudentRepository;
import com.course.portal.api.model.dto.StudentDTO;
import com.course.portal.api.security.PasswordSecurity;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(value = "/createUpdateStudent")
    public ResponseEntity<Response<StudentDTO>> createUpdateStudent(@RequestBody StudentDTO studentDTO){
        Response<StudentDTO> response = new Response<StudentDTO>();
        StudentEntity studentEntity = new StudentEntity();

        try{

            studentEntity.set_id(studentDTO.get_id());
            studentEntity.setName(studentDTO.getName());
            studentEntity.setEmail(studentDTO.getEmail());
            studentEntity.setPassword(PasswordSecurity.getPasswod(studentDTO.getPassword()));

            studentRepository.save(studentEntity);

            System.out.println("Dados salvo com sucesso");
            response.setData(studentDTO);
            return ResponseEntity.ok(response);

        }catch(HibernateException e){
            System.out.println("Erro ao salvar o estudante " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/getStudents")
    public ResponseEntity<Response<List<StudentDTO>>> getStudents(){

        Response<List<StudentDTO>> response = new Response<>();
        List<StudentDTO> studentDTOs = new ArrayList<>();
        try{

            List<StudentEntity> studentEntities = studentRepository.findAll();

            for(StudentEntity studentEntity : studentEntities){

                StudentDTO studentDTO = new StudentDTO();

                studentDTO.set_id(studentEntity.get_id());
                studentDTO.setName(studentEntity.getName());
                studentDTO.setEmail(studentEntity.getEmail());
                studentDTO.setPassword(studentEntity.getPassword());

                studentDTOs.add(studentDTO);
            }

            response.setData(studentDTOs);
            return ResponseEntity.ok(response);

        }catch(HibernateException e){
            System.out.println("Erro ao buscar os estudante " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
