package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.StepsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepsRepository extends JpaRepository<StepsEntity, Long> {
}
