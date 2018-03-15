package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
