package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.GridEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GridRepository extends JpaRepository<GridEntity, Long>{


}
