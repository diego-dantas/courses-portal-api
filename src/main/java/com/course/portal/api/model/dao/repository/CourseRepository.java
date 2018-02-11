package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long>{
}
