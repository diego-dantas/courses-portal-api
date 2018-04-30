package com.course.portal.api;

import com.course.portal.api.model.dao.entity.ProviderEntity;
import com.course.portal.api.model.dao.repository.ProviderRepository;
import com.course.portal.api.security.PasswordSecurity;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.portal.api.model.dao.entity.StateEntity;
import com.course.portal.api.model.dao.repository.StateRepository;


@RestController
@SpringBootApplication
public class ApiApplication {

	private int maxUploadSizeInMb = 10 * 1024 * 1024; // 10 MB

	public static void main(String[] args) {SpringApplication.run(ApiApplication.class, args);	}


	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatEmbedded() {

		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

		tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
			if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
				//-1 means unlimited
				((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
			}
		});

		return tomcat;

	}


//	@Autowired
//	private ProviderRepository providerRepository;
//	ProviderEntity providerEntity = new ProviderEntity();

	
	@GetMapping("/")
	public String getTeste(){
//		System.out.println("Tenho numeros " + providerRepository.count());
//
//		providerEntity.setEmail("admin");
//		providerEntity.setName("admin");
//		providerEntity.setPassword(PasswordSecurity.getPasswod("admin"));
//		try {
//			providerRepository.save(providerEntity);
//			System.out.println("Dados salvo");
//		}catch(HibernateException e) {
//			System.out.println("Erro " + e);
//		}
//		ProviderEntity providerEntity;
//		providerEntity = providerRepository.findByEmail("admin");
//		System.out.println("id e " + providerEntity.getName());
		return "API E-ODONTO DIGITAL EM EXECUÇÃO V1";
	}
}
