package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.GridEntity;
import com.course.portal.api.model.dao.entity.SubGridEntity;
import com.course.portal.api.model.dao.repository.GridRepository;
import com.course.portal.api.model.dao.repository.SubGridRepositoy;
import com.course.portal.api.model.dto.GridDTO;
import com.course.portal.api.model.dto.SubGridDTO;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api")
public class SubGridController {

    @Autowired
    private SubGridRepositoy subGridRepositoy;
    @Autowired
    private GridRepository gridRepository;

    @PostMapping(value = "/createSubGrid")
    public ResponseEntity<Response<SubGridDTO>> createSubGrid(@RequestBody SubGridDTO subGridDTO){

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
    
    @PostMapping(value = "/updateSubGrid")
    public ResponseEntity<Response<SubGridDTO>> updateSubGrid(@RequestBody SubGridDTO subGridDTO){
    	
    	Response<SubGridDTO> response = new Response<>();
    	GridEntity gridEntity = new GridEntity();
    	SubGridEntity subGridEntity = new SubGridEntity();
    	
    	try{
    		
    		gridEntity.set_id(subGridDTO.getGrid().get_id());
    		
    		subGridEntity.set_id(subGridDTO.get_id());
        	subGridEntity.setDescription(subGridDTO.getDescription());
        	subGridEntity.setGrid(gridEntity);
        	
        	
            subGridRepositoy.save(subGridEntity);
            response.setData(subGridDTO);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            System.out.println("Error Generico " + e );
        }

        return ResponseEntity.ok(response);
    }
    
    @PostMapping(value = "/deleteSubGrid")
    public ResponseEntity<Response<SubGridDTO>> deleteSubGrid(@RequestBody SubGridDTO subGridDTO){
    	
    	Response<SubGridDTO> response = new Response<>();
    	try {
    		
    		subGridRepositoy.delete(subGridDTO.get_id());
    		response.setData(subGridDTO);
    	}catch (Exception e){
            System.out.println("Erro ao deletar a subGrid " + e );
            
        }

    	return ResponseEntity.ok(response);
    }
    
    @GetMapping(value = "/getSubGrid")
    public ResponseEntity<Response<List<SubGridDTO>>> getSubGrid(){
    	
    	List<SubGridDTO> subGridDTOs = new ArrayList<>();
    	List<SubGridEntity> subGridEntities = new ArrayList<>();
    	GridEntity gridEntity = new GridEntity();
    	Response<List<SubGridDTO>> response = new Response<>();
    	
    	subGridEntities = subGridRepositoy.findAll();
    	
    	for(SubGridEntity subGridEntity : subGridEntities) {
    		
    		
    		SubGridDTO subGridDTO = new SubGridDTO();
    		GridDTO gridDTO = new GridDTO();
    		try {
    			//alimenta a grid dto
        		gridEntity = gridRepository.findOne(subGridEntity.getGrid().get_id());
        		gridDTO.set_id(gridEntity.get_id());
        		gridDTO.setDescription(gridEntity.getDescription());
        		
        		//alimenta a subgrid dto
        		subGridDTO.set_id(subGridEntity.get_id());
        		subGridDTO.setDescription(subGridEntity.getDescription());
        		subGridDTO.setGrid(gridDTO);
        		
        		subGridDTOs.add(subGridDTO);
    		}catch(Exception e ) {
    			System.out.println("Erro ao alimentar o array de subgrid " + e);
    		}
    		
    	}
    	
    	response.setData(subGridDTOs);
    	
    	return ResponseEntity.ok(response);
    }
}