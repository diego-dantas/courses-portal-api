package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>{
    
}