package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import com.course.portal.api.model.dao.entity.StudentEntity;
import com.course.portal.api.model.dao.repository.ConfigEmailRepository;
import com.course.portal.api.model.dao.repository.StudentRepository;
import com.course.portal.api.model.dto.StudentDTO;
import com.course.portal.api.security.PasswordSecurity;
import com.course.portal.api.useful.email.Email;

import org.apache.commons.mail.EmailException;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ConfigEmailRepository configEmailRepository;

    
    @PostMapping(value = "/createUpdateStudent")
    public ResponseEntity<Response<StudentDTO>> createUpdateStudent(@RequestBody StudentDTO studentDTO) throws MalformedURLException, EmailException{
        Response<StudentDTO> response = new Response<StudentDTO>();
        StudentEntity studentEntity = new StudentEntity();

        try{
            
            StudentEntity student = studentRepository.findByEmail(studentDTO.getEmail());
            if(student == null){
                studentEntity.setName(studentDTO.getName());
                studentEntity.setEmail(studentDTO.getEmail());
                studentEntity.setPassword(PasswordSecurity.getPasswod(studentDTO.getPassword()));
                studentEntity.setStatus(false);
                
                studentEntity = studentRepository.save(studentEntity);
                
                studentDTO.set_id(studentEntity.get_id());
                sendEmailConfirm(studentEntity.getEmail());
            }
            

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


    public void sendEmailConfirm(String emailStudent) throws MalformedURLException, EmailException{
        ConfigEmailEntity configEmailEntity = configEmailRepository.findOne(1L);
        String htmlEmail = "<p> Seja bem-vindo a <h2> E-odonto digital </h2></p> ";
		Email.sendHtmlEmail(
            emailStudent,  
            "Seja bem-vindo !!", 
            htmlEmail, 
            configEmailEntity);
    }

}
