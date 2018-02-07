package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {
}
