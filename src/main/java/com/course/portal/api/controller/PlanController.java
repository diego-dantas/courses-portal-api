package com.course.portal.api.controller;


import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.PlanEntity;
import com.course.portal.api.model.dao.repository.PlanRepository;
import com.course.portal.api.model.dto.PlanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PlanController {


    @Autowired
    private PlanRepository planRepository;

    @PostMapping(value = "/createUpdatePlan")
    public ResponseEntity<Response<PlanDTO>> createUpdatePlan(@RequestBody PlanDTO planDTO){

        Response<PlanDTO> response = new Response<>();
        PlanEntity planEntity = new PlanEntity();
        try {
            planEntity.set_id(planDTO.get_id());
            planEntity.setDescription(planDTO.getDescription());
            planEntity.setWayImage(planDTO.getWayImage());
            planEntity.setStatus(planDTO.isStatus());
            planRepository.save(planEntity);

            response.setData(planDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            System.out.println("Erro ao salvar um plano " + e);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }



    @PostMapping(value = "/deletePlan")
    public ResponseEntity<Response<PlanDTO>> deletePlan(@RequestBody PlanDTO planDTO){

        Response<PlanDTO> response = new Response<>();
        PlanEntity planEntity = new PlanEntity();

        try {
            planEntity.set_id(planDTO.get_id());
            planEntity.setDescription(planDTO.getDescription());
            planEntity.setWayImage(planDTO.getWayImage());
            planEntity.setStatus(planDTO.isStatus());
            planRepository.delete(planEntity);
            response.setData(planDTO);
        }catch (Exception e){
            System.out.println("Erro ao deletar o plano " + planDTO.get_id() + " Erro " +   e);
        }

        return ResponseEntity.ok(response);
    }



    @GetMapping(value = "/getPlan")
    public ResponseEntity<Response<List<PlanDTO>>> getPlan(){

        Response<List<PlanDTO>> response = new Response<>();
        List<PlanEntity> planEntites = new ArrayList<>();
        List<PlanDTO> planDTOS = new ArrayList<>();

        try {

            planEntites = planRepository.findAll();

            for(PlanEntity planEntity : planEntites){

                PlanDTO planDTO = new PlanDTO();

                planDTO.set_id(planEntity.get_id());
                planDTO.setDescription(planEntity.getDescription());
                planDTO.setWayImage(planEntity.getWayImage());
                planDTO.setStatus(planEntity.isStatus());

                planDTOS.add(planDTO);
            }

            response.setData(planDTOS);
        }catch (Exception e){
            System.out.println("Erro ao buscar os plano " +  e);
        }

        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/plan")
    public ResponseEntity<Response<PlanDTO>> updatePlanImg(@RequestBody PlanDTO planDTO){
        Response<PlanDTO> response = new Response<>();


        try{
            PlanEntity planEntity = planRepository.findOne(planDTO.get_id());
            planEntity.setWayImage(planDTO.getWayImage());
            planRepository.save(planEntity);
            response.setData(planDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
