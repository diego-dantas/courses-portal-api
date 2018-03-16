package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.EvaluationEntity;
import com.course.portal.api.model.dao.entity.EvaluationQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationQuestionRepository extends JpaRepository<EvaluationQuestionEntity, Long>{

    List<EvaluationQuestionEntity> findByEvaluation(EvaluationEntity evaluationEntity);
}
