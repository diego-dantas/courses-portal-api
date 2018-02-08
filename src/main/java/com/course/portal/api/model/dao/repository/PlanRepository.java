package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<PlanEntity, Long>{
}
