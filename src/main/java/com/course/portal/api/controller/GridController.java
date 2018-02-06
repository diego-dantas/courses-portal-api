package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.GridEntity;
import com.course.portal.api.model.dao.entity.ProviderEntity;
import com.course.portal.api.model.dao.repository.GridRepository;
import com.course.portal.api.model.dto.GridDTO;
import com.course.portal.api.model.dto.ProviderDTO;
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

    @PostMapping(value = "/updateGrid")
    public ResponseEntity<Response<GridDTO>> updateGrid(@RequestBody GridDTO gridDTO){
        System.out.println("To aqui mano "  +  gridDTO.get_id() + " texte " + gridDTO.getDescription());

        GridEntity gridEntity = new GridEntity();
        ProviderEntity providerEntity = new ProviderEntity();
        Response<GridDTO> response = new Response<>();

        providerEntity.set_id(gridDTO.getProvider().get_id());

        gridEntity.setProvider(providerEntity);
        gridEntity.set_id(gridDTO.get_id());
        gridEntity.setDescription(gridDTO.getDescription());

        gridRepository.save(gridEntity);
        response.setData(gridDTO);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/deleteGrid")
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
        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.set_id(1L);


        List<GridDTO> gridDTOs = new ArrayList<>();


        gridEntities = gridRepository.findAll();


        for(GridEntity gridEntity: gridEntities){

            GridDTO gridDTO = new GridDTO();

            gridDTO.set_id(gridEntity.get_id());
            gridDTO.setDescription(gridEntity.getDescription());
            gridDTO.setProvider(providerDTO);

            gridDTOs.add(gridDTO);
        }

        response.setData(gridDTOs);
        Gson gson = new Gson();

        String gridList = gson.toJson(gridDTOs);
        System.out.println(gridList);

        System.out.println("Carreguei os dados");

        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/getMenu")
    public String getMenu(){
        List<GridEntity> gridEntities = new ArrayList<>();

        gridEntities = gridRepository.getByGridSubGrid();
        Gson gson = new Gson();
        String teste = gson.toJson(gridEntities);
        System.out.println(teste);
//        for(GridEntity grid : gridEntities){
//            System.out.println(grid.get_id() + " desc " +  grid.getDescription());
//        }

        return "";
    }
}
