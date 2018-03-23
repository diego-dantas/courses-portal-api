package com.course.portal.api.controller;


import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.entity.CoursePromotionEntity;
import com.course.portal.api.model.dao.entity.PromotionEntity;
import com.course.portal.api.model.dao.repository.CoursePromotionRepository;
import com.course.portal.api.model.dto.CourseDTO;
import com.course.portal.api.model.dto.CoursePromotionDTO;
import com.course.portal.api.model.dto.PromotionDTO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CoursePromotionController {


    @Autowired
    private CoursePromotionRepository coursePromotionRepository;


    @PostMapping(value = "/saveCoursePromotion")
    public ResponseEntity<Response<List<CoursePromotionDTO>>> saveCoursePromotion(@RequestBody List<CoursePromotionDTO> coursePromotionDTOs){
        Response<List<CoursePromotionDTO>> response = new Response<>();


        try{
            PromotionEntity promotionEntity = new PromotionEntity();
            promotionEntity.set_id(coursePromotionDTOs.get(0).getPromotion().get_id());

            List<CoursePromotionEntity> coursePromotionEntities = coursePromotionRepository.findByPromotion(promotionEntity);

            for(CoursePromotionEntity coursePromotionEntity : coursePromotionEntities){

                coursePromotionRepository.delete(coursePromotionEntity.get_id());
            }

            for(CoursePromotionDTO coursePromotionDTO : coursePromotionDTOs){

                CoursePromotionEntity coursePromotionEntity = new CoursePromotionEntity();
                CourseEntity courseEntity = new CourseEntity();
                PromotionEntity promotionEntity1 = new PromotionEntity();

                courseEntity.set_id(coursePromotionDTO.getCourse().get_id());
                promotionEntity1.set_id(coursePromotionDTO.getPromotion().get_id());

                coursePromotionEntity.setCourse(courseEntity);
                coursePromotionEntity.setPromotion(promotionEntity1);
                coursePromotionEntity.set_id(coursePromotionDTO.get_id());

                coursePromotionRepository.save(coursePromotionEntity);
            }

            response.setData(coursePromotionDTOs);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao salvar os cursos da promoção " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "/deleteCoursePromotion")
    public ResponseEntity<Response<List<CoursePromotionDTO>>> deleteCoursePromotion(@RequestBody List<CoursePromotionDTO> coursePromotionDTOs){
        Response<List<CoursePromotionDTO>> response = new Response<>();

        try{

            PromotionEntity promotionEntity = new PromotionEntity();
            promotionEntity.set_id(coursePromotionDTOs.get(0).getPromotion().get_id());

            List<CoursePromotionEntity> coursePromotionEntities = coursePromotionRepository.findByPromotion(promotionEntity);

            for(CoursePromotionEntity coursePromotionEntity : coursePromotionEntities){

                coursePromotionRepository.delete(coursePromotionEntity.get_id());
            }


            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao Excluir os cursos da promoção " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/getCoursePromotion")
    public ResponseEntity<Response<List<CoursePromotionDTO>>> getCoursePromotion(){

        Response<List<CoursePromotionDTO>> response = new Response<>();
        List<CoursePromotionDTO> coursePromotionDTOs = new ArrayList<>();
         try{

             List<CoursePromotionEntity> coursePromotionEntities = coursePromotionRepository.findAll();

             for(CoursePromotionEntity coursePromotionEntity : coursePromotionEntities){
                 CourseDTO courseDTO = new CourseDTO();
                 PromotionDTO promotionDTO = new PromotionDTO();
                 CoursePromotionDTO coursePromotionDTO = new CoursePromotionDTO();

                 courseDTO.set_id(coursePromotionEntity.getCourse().get_id());
                 promotionDTO.set_id(coursePromotionEntity.getPromotion().get_id());

                 coursePromotionDTO.setCourse(courseDTO);
                 coursePromotionDTO.setPromotion(promotionDTO);
                 coursePromotionDTO.set_id(coursePromotionEntity.get_id());

                 coursePromotionDTOs.add(coursePromotionDTO);
             }

             response.setData(coursePromotionDTOs);
             return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao buscar os cursos das promoçoes " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
