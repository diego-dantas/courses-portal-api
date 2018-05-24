package com.course.portal.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.ProfileEntity;
import com.course.portal.api.model.dao.repository.ProfileRepository;
import com.course.portal.api.model.dto.ProfileDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ProfileController{

    @Autowired
    private ProfileRepository profileRepository;


    @GetMapping(value = "/getProfile")
    public ResponseEntity<Response<List<ProfileDTO>>> getProfile(){

        try {
            Response<List<ProfileDTO>> response = new Response<>();
            List<ProfileDTO> profileDTOs = new ArrayList<>();

            List<ProfileEntity> profileEntities = profileRepository.findAll();
            for(ProfileEntity profileEntity : profileEntities){
                ProfileDTO profileDTO = new ProfileDTO();

                profileDTO.set_id(profileEntity.get_id());
                profileDTO.setDescription(profileEntity.getDescription());

                profileDTOs.add(profileDTO);
            }
            
            response.setData(profileDTOs);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}