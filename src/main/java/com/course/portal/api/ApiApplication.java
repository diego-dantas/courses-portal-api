package com.course.portal.api;

import com.course.portal.api.model.dao.entity.ProviderEntity;
import com.course.portal.api.model.dao.repository.ProviderRepository;
import com.course.portal.api.security.PasswordSecurity;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.portal.api.model.dao.entity.StateEntity;
import com.course.portal.api.model.dao.repository.StateRepository;


@RestController
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {SpringApplication.run(ApiApplication.class, args);	}
	
	@Autowired
	private ProviderRepository providerRepository;
	ProviderEntity providerEntity = new ProviderEntity();

	
	@GetMapping("/")
	public String getTeste(){
		System.out.println("Tenho numeros " + providerRepository.count());
		providerEntity.setEmail("admin@courses.com.br");
		providerEntity.setName("admin");
		providerEntity.setPassword(PasswordSecurity.getPasswod("admin"));
		try {
			providerRepository.save(providerEntity);
			System.out.println("Dados salvo");
		}catch(HibernateException e) {
			System.out.println("Erro " + e);
		}
		ProviderEntity providerEntity;
		providerEntity = providerRepository.findByEmail("admin@courses.com.br");
		System.out.println("id e " + providerEntity.getName());
		return "Deu certo mano";
	}
}
