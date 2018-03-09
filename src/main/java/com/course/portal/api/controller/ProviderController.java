package com.course.portal.api.controller;

import com.course.portal.api.model.dao.entity.ProviderEntity;
import com.course.portal.api.model.dao.repository.ProviderRepository;
import com.course.portal.api.security.PasswordSecurity;
import com.course.portal.api.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dto.ProviderDTO;


@RestController
@RequestMapping(value = "/api")
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<Response<ProviderDTO>> loginProvider(@RequestBody ProviderDTO providerDTO){

        Response<ProviderDTO> response = new Response<ProviderDTO>();
        ProviderEntity providerEntity = providerRepository.findByEmail(providerDTO.getEmail());

        //valido a senha para da retorno
        if(providerEntity.getEmail().equals(providerDTO.getEmail()) &&
                PasswordSecurity.validatePassword(providerDTO.getPassword(), providerEntity.getPassword())){

            providerDTO.set_id(providerEntity.get_id());
            providerDTO.setName(providerEntity.getName());
            System.out.println("validou a senha");
            response.setData(providerDTO);
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
	}


	@PostMapping(value = "updateProvider")
    public ResponseEntity<Response<ProviderDTO>> updateProvider(@RequestBody ProviderDTO providerDTO){

        Response<ProviderDTO> response = new Response<>();
        ProviderEntity providerEntity = new ProviderEntity();

        try{
            providerEntity.set_id(providerDTO.get_id());
            providerEntity.setName(providerDTO.getName());
            providerEntity.setEmail(providerDTO.getEmail());
            providerEntity.setPassword(PasswordSecurity.getPasswod(providerDTO.getPassword()));

            providerRepository.save(providerEntity);

            response.setData(providerDTO);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            System.out.println("Erro ao alterar os dados do Provider " + e);
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
