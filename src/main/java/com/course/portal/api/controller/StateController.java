package com.course.portal.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.StateEntity;
import com.course.portal.api.model.dao.repository.StateRepository;
import com.course.portal.api.model.dto.StateDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class StateController {

    @Autowired
    private StateRepository stateRepository;


    @GetMapping(value = "/getState")
    public ResponseEntity<Response<List<StateDTO>>> getState(){

        Response<List<StateDTO>> response = new Response<>();
        List<StateDTO> stateDTOs = new ArrayList<>();
        try {

            List<StateEntity> stateEntities = stateRepository.findAll();
            for(StateEntity stateEntity : stateEntities){
                StateDTO stateDTO = new StateDTO();

                stateDTO.set_id(stateEntity.get_id());
                stateDTO.setName(stateEntity.getName());
                stateDTO.setState(stateEntity.getState());

                stateDTOs.add(stateDTO);
            }

            response.setData(stateDTOs);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}