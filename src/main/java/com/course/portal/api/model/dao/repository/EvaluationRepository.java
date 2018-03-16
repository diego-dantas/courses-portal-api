package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long> {
}
