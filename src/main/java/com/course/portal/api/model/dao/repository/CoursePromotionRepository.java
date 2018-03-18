package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.CoursePromotionEntity;
import com.course.portal.api.model.dao.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursePromotionRepository extends JpaRepository<CoursePromotionEntity, Long> {

    List<CoursePromotionEntity> findByPromotion(PromotionEntity promotionEntity);
}
