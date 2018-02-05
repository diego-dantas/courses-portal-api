package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.GridEntity;
import com.course.portal.api.model.dao.entity.SubGridEntity;
import com.course.portal.api.model.dao.repository.SubGridRepositoy;
import com.course.portal.api.model.dto.GridDTO;
import com.course.portal.api.model.dto.SubGridDTO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api")
public class SubGridController {

    @Autowired
    private SubGridRepositoy subGridRepositoy;

    @PostMapping(value = "/createSubGrid")
    public ResponseEntity<Response<SubGridDTO>> createSubGrid(@RequestBody SubGridDTO subGridDTO){

        System.out.println(subGridDTO.getGrid().get_id() + " codigo da grid");
        GridEntity gridEntity = new GridEntity();
        SubGridEntity subGridEntity = new SubGridEntity();

        Response<SubGridDTO> response = new Response<>();

        gridEntity.set_id(subGridDTO.getGrid().get_id());


        subGridEntity.setDescription(subGridDTO.getDescription());
        subGridEntity.setGrid(gridEntity);
        try{
            subGridRepositoy.saveAndFlush(subGridEntity);
            response.setData(subGridDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            System.out.println("Error Generico " + e );
        }

        return ResponseEntity.ok(response);

    }
}
