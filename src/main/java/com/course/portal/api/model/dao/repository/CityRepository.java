package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.CityEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Long>{
    
}