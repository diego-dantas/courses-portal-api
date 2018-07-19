package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.ConfigEmailEntity;
import com.course.portal.api.model.dao.entity.PlanEntity;
import com.course.portal.api.model.dao.entity.ProfileEntity;
import com.course.portal.api.model.dao.entity.StudentEntity;
import com.course.portal.api.model.dao.repository.ConfigEmailRepository;
import com.course.portal.api.model.dao.repository.StudentRepository;
import com.course.portal.api.model.dto.PlanDTO;
import com.course.portal.api.model.dto.ProfileDTO;
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

    
    @PostMapping(value = "/createStudent")
    public ResponseEntity<Response<StudentDTO>> createStudent(@RequestBody StudentDTO studentDTO){
        Response<StudentDTO> response = new Response<StudentDTO>();
        StudentEntity studentEntity = new StudentEntity();

        try{
            
            StudentEntity student = studentRepository.findByEmail(studentDTO.getEmail());
            if(student == null){
                studentEntity.setName(studentDTO.getName());
                studentEntity.setEmail(studentDTO.getEmail());
                studentEntity.setPassword(PasswordSecurity.getPasswod(studentDTO.getPassword()));
                studentEntity.setImagePath(studentDTO.getImagePath());
                studentEntity.setSource(studentDTO.getSource());
                studentEntity.setStatus(false);
                
                studentEntity = studentRepository.save(studentEntity);
                
                studentDTO.set_id(studentEntity.get_id());
            }
            

            response.setData(studentDTO);
            return ResponseEntity.ok(response);

        }catch(HibernateException e){
            System.out.println("Erro ao salvar o estudante " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/updateStudent")
    public ResponseEntity<Response<StudentDTO>> updateStudent(@RequestBody StudentDTO studentDTO) throws MalformedURLException, EmailException{
        Response<StudentDTO> response = new Response<StudentDTO>();
        StudentEntity studentEntity = new StudentEntity();
        ProfileEntity profileEntity = new ProfileEntity();
        PlanEntity planEntity = new PlanEntity();
        try{

            studentEntity.set_id(studentDTO.get_id());
            studentEntity.setName(studentDTO.getName());
            studentEntity.setEmail(studentDTO.getEmail());
            studentEntity.setPassword(PasswordSecurity.getPasswod(studentDTO.getPassword()));
            studentEntity.setZipCode(studentDTO.getZipCode());
            studentEntity.setStreet(studentDTO.getStreet());
            studentEntity.setNumber(studentDTO.getNumber());
            studentEntity.setNeighborhood(studentDTO.getNeighborhood());
            studentEntity.setPhone(studentDTO.getPhone());
            studentEntity.setCellPhone(studentDTO.getCellPhone());
            studentEntity.setSexo(studentDTO.getSexo());
            studentEntity.setNews(studentDTO.getNews());
            studentEntity.setRg(studentDTO.getRg());
            studentEntity.setCpf(studentDTO.getCpf());
            studentEntity.setOutro(studentDTO.getOutro());
            studentEntity.setComple(studentDTO.getComple());
            studentEntity.setState(studentDTO.getState());
            studentEntity.setCity(studentDTO.getCity());
            studentEntity.setStatus(studentDTO.isStatus());
            studentEntity.setSource(studentDTO.getSource());
            studentEntity.setImagePath(studentDTO.getImagePath());
            
            
            profileEntity.set_id(studentDTO.getProfile().get_id());
            studentEntity.setProfile(profileEntity);
            
            //lembrar de alterar a logica quando implementar o plano
            planEntity.set_id(1L);
            studentEntity.setPlan(planEntity);

            studentRepository.save(studentEntity);
            
            //sendEmailConfirm(studentEntity.getEmail());
            

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

    @PostMapping(value = "/loginStudent")
    public ResponseEntity<Response<StudentDTO>> loginStudent(@RequestBody StudentDTO studentDTO){

        System.out.println("to aquii " + studentDTO.getEmail());
        System.out.println("to aquii " + studentDTO.getPassword());
        Response<StudentDTO> response = new Response<StudentDTO>();
        
        StudentEntity student = studentRepository.findByEmail(studentDTO.getEmail());
        
         try {

             //valido a senha para da retorno
             if(student.getEmail().equals(studentDTO.getEmail()) &&
                PasswordSecurity.validatePassword(studentDTO.getPassword(), student.getPassword())){
                
                if(student.isStatus()){
                    studentDTO.set_id(student.get_id());
                    studentDTO.setName(student.getName());
                    studentDTO.setEmail(student.getEmail());
                    studentDTO.setZipCode(student.getZipCode());
                    studentDTO.setStreet(student.getStreet());
                    studentDTO.setNumber(student.getNumber());
                    studentDTO.setNeighborhood(student.getNeighborhood());
                    studentDTO.setPhone(student.getPhone());
                    studentDTO.setCellPhone(student.getCellPhone());
                    studentDTO.setSexo(student.getSexo());
                    studentDTO.setNews(student.getNews());
                    studentDTO.setRg(student.getRg());
                    studentDTO.setCpf(student.getCpf());
                    studentDTO.setOutro(student.getOutro());
                    studentDTO.setComple(student.getComple());
                    studentDTO.setState(student.getState());
                    studentDTO.setCity(student.getCity());
                    studentDTO.setStatus(student.isStatus());
                    studentDTO.setSource(student.getSource());
                    studentDTO.setImagePath(student.getImagePath());
                    
                    PlanDTO planDTO = new PlanDTO();
                    planDTO.set_id(student.getPlan().get_id());
                    studentDTO.setPlan(planDTO);

                    ProfileDTO profileDTO = new ProfileDTO();
                    profileDTO.set_id(student.getProfile().get_id());
                    studentDTO.setProfile(profileDTO);
                }else {
                    studentDTO.set_id(student.get_id());
                    studentDTO.setName(student.getName());
                    studentDTO.setImagePath(student.getImagePath());
                    studentDTO.setSource(student.getSource());

                    PlanDTO planDTO = new PlanDTO();
                    planDTO.set_id(student.getPlan().get_id());
                    studentDTO.setPlan(planDTO);

                }
                

                response.setData(studentDTO);
                return ResponseEntity.ok(response);
            }else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
	}


    public void sendEmailConfirm(String emailStudent) throws MalformedURLException, EmailException{
        try {
            ConfigEmailEntity configEmailEntity = configEmailRepository.findOne(1L);
            String htmlEmail = "<p> Seja bem-vindo a <h2> E-odonto digital </h2></p> ";
		    Email.sendHtmlEmail(
                emailStudent,  
                "Seja bem-vindo !!", 
                htmlEmail, 
                configEmailEntity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
