package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.PromotionEntity;
import com.course.portal.api.model.dao.repository.PromotionRepository;
import com.course.portal.api.model.dto.PromotionDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PromotionController {

    @Autowired
    private PromotionRepository promotionRepository;


    @PostMapping(value = "/createPromotion")
    public ResponseEntity<Response<PromotionDTO>> createPromotion(@RequestBody PromotionDTO promotionDTO){

        Response<PromotionDTO> response = new Response<>();
        PromotionEntity promotionEntity = new PromotionEntity();

        try {

            promotionEntity.setCodigoCupom(promotionDTO.getCodigoCupom());
            promotionEntity.setDescription(promotionDTO.getDescription());
            promotionEntity.setDateInicial(promotionDTO.getDateInicial());
            promotionEntity.setDateFinal(promotionDTO.getDateFinal());
            promotionEntity.setPercentual(promotionDTO.getPercentual());

            promotionRepository.save(promotionEntity);
            response.setData(promotionDTO);

        }catch (Exception e){
            System.out.println("Erro ao criar uma nova promoção " + e);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/updatePromotion")
    public ResponseEntity<Response<PromotionDTO>> updatePromotion(@RequestBody PromotionDTO promotionDTO){

        Response<PromotionDTO> response = new Response<>();
        PromotionEntity promotionEntity = new PromotionEntity();

        System.out.println(promotionDTO.get_id() +  " id da promoção");

        try {
            promotionEntity.set_id(promotionDTO.get_id());
            promotionEntity.setCodigoCupom(promotionDTO.getCodigoCupom());
            promotionEntity.setDescription(promotionDTO.getDescription());
            promotionEntity.setDateInicial(promotionDTO.getDateInicial());
            promotionEntity.setDateFinal(promotionDTO.getDateFinal());
            promotionEntity.setPercentual(promotionDTO.getPercentual());

            promotionRepository.save(promotionEntity);
            response.setData(promotionDTO);

        }catch (Exception e){
            System.out.println("Erro ao alterar a promoção " + e);
        }

        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/deletePromotion")
    public ResponseEntity<Response<PromotionDTO>> deletePromotion(@RequestBody PromotionDTO promotionDTO){

        Response<PromotionDTO> response = new Response<>();

        System.out.println(promotionDTO.get_id() +  " id da promoção");

        try {
            promotionRepository.delete(promotionDTO.get_id());
            response.setData(promotionDTO);

        }catch (Exception e){
            System.out.println("Erro ao deletar a promoção " + e);
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/getPromotion")
    public String getPromotion(){
//    public ResponseEntity<String> getPromotion(){

        Response<String> response = new Response<>();
        List<PromotionEntity> promotionEntity = new ArrayList<>();
        List<PromotionDTO> promotionDTOS = new ArrayList<>();
        Gson gson = new Gson();
        String go = "";

        try {

            promotionEntity = promotionRepository.findAll();

            for(PromotionEntity pro : promotionEntity){
                PromotionDTO promotionDTO = new PromotionDTO();
                System.out.println(pro.getDateInicial());

                promotionDTO.set_id(pro.get_id());
                promotionDTO.setCodigoCupom(pro.getCodigoCupom());
                promotionDTO.setDescription(pro.getDescription());
                promotionDTO.setDateInicial(pro.getDateInicial());
                promotionDTO.setDateFinal(pro.getDateFinal());
                promotionDTO.setPercentual(pro.getPercentual());


                promotionDTOS.add(promotionDTO);
            }

            go = gson.toJson(promotionDTOS);
            System.out.println(go);


            response.setData(go);

        }catch (Exception e){
            System.out.println("Erro ao buscar as promoções " + e);
        }

        //return ResponseEntity.ok(go);
        return go;
    }



}
