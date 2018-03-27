package com.course.portal.api.controller;


import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.entity.MaterialEntity;
import com.course.portal.api.model.dao.entity.MaterialPathEntity;
import com.course.portal.api.model.dao.entity.StepsEntity;
import com.course.portal.api.model.dao.repository.CourseRepository;
import com.course.portal.api.model.dao.repository.MaterialPathRepository;
import com.course.portal.api.model.dao.repository.MaterialRepository;
import com.course.portal.api.model.dao.repository.StepsRepository;
import com.course.portal.api.model.dto.CourseDTO;
import com.course.portal.api.model.dto.MaterialDTO;
import com.course.portal.api.model.dto.MaterialPathDTO;
import com.course.portal.api.model.dto.StepsDTO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MaterialController {


    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private StepsRepository stepsRepository;
    @Autowired
    private CourseRepository courseRepository;



    @PostMapping(value = "/createUpdateMaterial")
    public ResponseEntity<Response<MaterialDTO>> createUpdateMaterial(@RequestBody MaterialDTO materialDTO){

        Response<MaterialDTO> response = new Response<>();
        MaterialEntity materialEntity = new MaterialEntity();
        StepsEntity stepsEntity = new StepsEntity();

        try{
            stepsEntity.set_id(materialDTO.getSteps().get_id());
            materialEntity.setSteps(stepsEntity);

            materialEntity.set_id(materialDTO.get_id());
            materialEntity.setName(materialDTO.getName());
            materialEntity.setType(materialDTO.getType());
            materialEntity.setUrl(materialDTO.getUrl());
            materialEntity.setMaterialOrder(materialDTO.getMaterialOrder());
            materialEntity.setDownload(materialDTO.isDownload());
            materialEntity.setStatus(materialDTO.isStatus());

            materialRepository.save(materialEntity);
            response.setData(materialDTO);
            return ResponseEntity.ok(response);
        }catch (HibernateException e){
            System.out.println("Erro ao salvar o material do step " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/deleteMaterial")
    public ResponseEntity<Response<MaterialDTO>> deleteMaterial(@RequestBody MaterialDTO materialDTO){

        Response<MaterialDTO> response = new Response<>();
        MaterialEntity materialEntity = new MaterialEntity();
        StepsEntity stepsEntity = new StepsEntity();

        try{
            stepsEntity.set_id(materialDTO.getSteps().get_id());
            materialEntity.setSteps(stepsEntity);

            materialEntity.set_id(materialDTO.get_id());
            materialEntity.setName(materialDTO.getName());
            materialEntity.setType(materialDTO.getType());
            materialEntity.setUrl(materialDTO.getUrl());
            materialEntity.setMaterialOrder(materialDTO.getMaterialOrder());
            materialEntity.setDownload(materialDTO.isDownload());
            materialEntity.setStatus(materialDTO.isStatus());

            materialRepository.delete(materialEntity);
            response.setData(materialDTO);
            return ResponseEntity.ok(response);
        }catch (HibernateException e){
            System.out.println("Erro ao excluir o material do step " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/getMaterial")
    public ResponseEntity<Response<List<MaterialDTO>>> getMaterial(){

        Response<List<MaterialDTO>> response = new Response<>();
        List<MaterialDTO> materialDTOS = new ArrayList<>();
        List<MaterialEntity> materialEntities = new ArrayList<>();

        try{

            materialEntities = materialRepository.findAll();

            for(MaterialEntity materialEntity : materialEntities){

                MaterialDTO materialDTO = new MaterialDTO();
                StepsDTO stepsDTO       = new StepsDTO();
                CourseDTO courseDTO     = new CourseDTO();

                StepsEntity stepsEntity   = stepsRepository.findOne(materialEntity.getSteps().get_id());
                CourseEntity courseEntity = courseRepository.findOne(stepsEntity.getCourse().get_id());

                //courseEntity para courseDTO
                courseDTO.set_id(courseEntity.get_id());
                courseDTO.setDescription(courseEntity.getDescription());
                courseDTO.setName(courseEntity.getName());
                courseDTO.setPrice(courseEntity.getPrice());
                courseDTO.setObjective(courseEntity.getObjective());
                courseDTO.setHours(courseEntity.getHours());
                courseDTO.setStatus(courseEntity.isStatus());
                courseDTO.setWayImage(courseEntity.getWayImage());

                //StepsEntity para StepsDTO
                stepsDTO.set_id(stepsEntity.get_id());
                stepsDTO.setDescription(stepsEntity.getDescription());
                stepsDTO.setName(stepsEntity.getName());
                stepsDTO.setStepsOrder(stepsEntity.getStepsOrder());
                stepsDTO.setCourse(courseDTO);

                //materialEntity
                materialDTO.setSteps(stepsDTO);
                materialDTO.set_id(materialEntity.get_id());
                materialDTO.setName(materialEntity.getName());
                materialDTO.setType(materialEntity.getType());
                materialDTO.setUrl(materialEntity.getUrl());
                materialDTO.setMaterialOrder(materialEntity.getMaterialOrder());
                materialDTO.setDownload(materialEntity.isDownload());
                materialDTO.setStatus(materialEntity.isStatus());


                materialDTOS.add(materialDTO);
            }


            response.setData(materialDTOS);
            return ResponseEntity.ok(response);
        }catch(HibernateException e){
            System.out.println("Erro ao buscar o material do curso " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
