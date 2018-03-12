package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.MaterialEntity;
import com.course.portal.api.model.dao.entity.StepsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<MaterialEntity, Long> {

    List<MaterialEntity> findBySteps(StepsEntity stepsEntity);
}
