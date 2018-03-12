package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.entity.StepsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StepsRepository extends JpaRepository<StepsEntity, Long> {


    List<StepsEntity> findByCourse(CourseEntity courseEntity);
}
