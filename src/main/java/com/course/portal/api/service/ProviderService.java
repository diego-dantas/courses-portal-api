package com.course.portal.api.service;

import com.course.portal.api.model.dao.entity.ProviderEntity;
import com.course.portal.api.model.dao.repository.ProviderRepository;
import com.course.portal.api.model.dto.ProviderDTO;
import com.course.portal.api.security.PasswordSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public boolean validateLogin(ProviderDTO providerDTO){
        System.out.println("To aqui vamos iniciar o metodo");
        System.out.println("Tenho numeros " + providerRepository.count());

        boolean validateReturn = true;
        try{
            //ProviderEntity providerEntity = null;
            System.out.println("Eu tenho o e-mail " + providerDTO.getEmail() );


            //System.out.println("Tenho numeros" + providerRepository.count());
            //System.out.println("Email e senha entity" + providerEntity.getEmail() + " " + providerEntity.getPassword());
           /* if(providerEntity.getEmail().equals(providerDTO.getEmail()) &&
                    PasswordSecurity.validatePassword(providerDTO.getPassword(), providerEntity.getPassword())){
                validateReturn =  true;
            }else{
                validateReturn = false;
            }*/
        }catch(Exception e){
            System.out.println("Erro ao validar o login " + e);
        }

        return validateReturn;
    }
}
