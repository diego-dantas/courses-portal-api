package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.MaterialEntity;
import com.course.portal.api.model.dao.entity.MaterialPathEntity;
import com.course.portal.api.model.dao.repository.MaterialPathRepository;
import com.course.portal.api.model.dto.MaterialDTO;
import com.course.portal.api.model.dto.MaterialPathDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class MaterialPathController {

    @Autowired
    private MaterialPathRepository materialPathRepository;

    @PostMapping(value = "/material")
    public ResponseEntity<Response<MaterialPathDTO>> saveMaterial(@RequestBody List<MaterialPathDTO> materialPathDTOs){
        Response<MaterialPathDTO> response = new Response<>();
        try{

            for(MaterialPathDTO materialPathDTO : materialPathDTOs){

                MaterialEntity materialEntity = new MaterialEntity();
                MaterialPathEntity materialPathEntity = new MaterialPathEntity();

                materialEntity.set_id(materialPathDTO.getMaterial().get_id());

                materialPathEntity.setMaterial(materialEntity);
                materialPathEntity.setImagePath(materialPathDTO.getImagePath());

                materialPathRepository.save(materialPathEntity);
                response.setData(materialPathDTO);
            }


            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/deleteMaterialPath")
    public ResponseEntity<Response<MaterialPathDTO>> deleteMaterialPath(@RequestBody MaterialPathDTO materialPathDTO){
        Response<MaterialPathDTO> response = new Response<>();
        try{
            materialPathRepository.delete(materialPathDTO.get_id());
            response.setData(materialPathDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getMaterialPath")
    public ResponseEntity<Response<List<MaterialPathDTO>>> getMaterialPath(){

        Response<List<MaterialPathDTO>> response = new Response<>();

        List<MaterialPathDTO> materialPathDTOS = new ArrayList<>();

        try{

            List<MaterialPathEntity> materialPathEntities = materialPathRepository.findAll();

            for(MaterialPathEntity materialPathEntity : materialPathEntities){

                MaterialPathDTO materialPathDTO = new MaterialPathDTO();
                MaterialDTO materialDTO = new MaterialDTO();

                materialDTO.set_id(materialPathEntity.getMaterial().get_id());

                materialPathDTO.set_id(materialPathEntity.get_id());
                materialPathDTO.setImagePath(materialPathEntity.getImagePath());
                materialPathDTO.setMaterial(materialDTO);

                materialPathDTOS.add(materialPathDTO);
            }


            response.setData(materialPathDTOS);

            return ResponseEntity.ok(response);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
