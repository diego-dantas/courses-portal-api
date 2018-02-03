package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.GridEntity;
import com.course.portal.api.model.dao.entity.ProviderEntity;
import com.course.portal.api.model.dao.repository.GridRepository;
import com.course.portal.api.model.dto.GridDTO;
import com.google.gson.Gson;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class GridController {

    @Autowired
    private GridRepository gridRepository;

    @PostMapping(value = "/createGrid")
    public ResponseEntity<Response<GridDTO>> createGrid(@RequestBody GridDTO gridDTO){

        Response<GridDTO> response = new Response<>();
        GridEntity gridEntity = new GridEntity();
        ProviderEntity providerEntity = new ProviderEntity();


        providerEntity.set_id(gridDTO.getProvider().get_id());

        gridEntity.setDescription(gridDTO.getDescription());
        gridEntity.setProvider(providerEntity);
        try {
            gridRepository.save(gridEntity);
            System.out.println("Dados salvo");


        }catch(HibernateException e) {
            System.out.println("Erro " + e);
        }
        response.setData(gridDTO);
        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "deleteGrid")
    public ResponseEntity<Response<GridDTO>> deleteGrid(@RequestBody GridDTO gridDTO){
        Response<GridDTO> response = new Response<>();
        gridRepository.delete(gridDTO.get_id());
        response.setData(gridDTO);
        return ResponseEntity.ok(response);
    }



    @GetMapping(value = "/getGrid")
    public ResponseEntity<Response<List<GridDTO>>> getGrid(){
        List<GridEntity> gridEntities = new ArrayList<>();
        Response<List<GridDTO>> response = new Response<>();

        List<GridDTO> gridDTOs = new ArrayList<>();


        gridEntities = gridRepository.findAll();

        for(GridEntity gridEntity: gridEntities){

            GridDTO gridDTO = new GridDTO();

            gridDTO.set_id(gridEntity.get_id());
            gridDTO.setDescription(gridEntity.getDescription());

            gridDTOs.add(gridDTO);
        }

        response.setData(gridDTOs);
        Gson gson = new Gson();


        //gridDTO.setProvider();
        String gridList = gson.toJson(gridDTOs);
        System.out.println(gridList);



        return ResponseEntity.ok(response);
    }
}
