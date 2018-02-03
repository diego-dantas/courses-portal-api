package com.course.portal.api.service;

import com.course.portal.api.model.dao.entity.ProviderEntity;
import com.course.portal.api.model.dao.repository.ProviderRepository;
import com.course.portal.api.model.dto.ProviderDTO;
import com.course.portal.api.security.PasswordSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@Configurable
public class ProviderService{


    public boolean validateLogin(ProviderDTO providerDTO){
        return true;
    }
}
