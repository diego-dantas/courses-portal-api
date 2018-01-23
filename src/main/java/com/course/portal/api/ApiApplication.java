package com.course.portal.api;

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
	
/*	@Autowired
	private StateRepository stateRepository;
	StateEntity stateEntity = new StateEntity();*/
	
	@GetMapping("/")
	public String getTeste(){
	/*	stateEntity.setState("Teste Estadoa");
		System.out.println(stateEntity.getState());
		try {
			System.out.println("Numero de registro " + stateRepository.count());
			stateRepository.save(stateEntity);
			System.out.println("Numero de registro " + stateRepository.count());
		}catch(HibernateException e) {
			System.out.println("Erro " + e);
		}*/
		
		return "Retorno do controller";
	}
}
