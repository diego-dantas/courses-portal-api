package com.course.portal.api.controller;

import com.course.portal.api.model.dao.entity.ProviderEntity;
import com.course.portal.api.model.dao.repository.ProviderRepository;
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
    ProviderEntity providerEntity = new ProviderEntity();


    @GetMapping("/")
    public String getTeste(){
        System.out.println("Tenho numeros" + providerRepository.count());
		/*providerEntity.setEmail("admin@courses.com.br");
		providerEntity.setName("admin");
		providerEntity.setPassword(PasswordSecurity.getPasswod("admin"));
		try {
			providerRepository.save(providerEntity);
			System.out.println("Dados salvo");
		}catch(HibernateException e) {
			System.out.println("Erro " + e);
		}*/

        return "Deu certo mano";
    }
	
	@PostMapping(value = "/login")
	public ResponseEntity<Response<ProviderDTO>> loginProvider(@RequestBody ProviderDTO providerDTO){

        System.out.println("Tenho numeros" + providerRepository.count());
        ProviderEntity providerEntity = new ProviderEntity();
        providerEntity = providerRepository.findByEmail(providerDTO.getEmail());

		Response<ProviderDTO> response = new Response<ProviderDTO>();
		ProviderService providerService = new ProviderService();
        //return ResponseEntity.ok(response);

		if(providerService.validateLogin(providerDTO)) {
			response.setData(providerDTO);
			return ResponseEntity.ok(response);
		}else {
			response.setData(providerDTO);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
