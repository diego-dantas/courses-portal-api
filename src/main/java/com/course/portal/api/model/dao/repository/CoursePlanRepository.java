package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.CourseEntity;
import com.course.portal.api.model.dao.entity.CoursePlanEntity;
import com.course.portal.api.model.dao.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursePlanRepository extends JpaRepository<CoursePlanEntity, Long>{

    List<CoursePlanEntity> findByPlan(PlanEntity planEntity);

    int countByCourseAndPlan(CourseEntity courseEntity, PlanEntity planEntity);

    CoursePlanEntity findByCourseAndPlan(CourseEntity courseEntity, PlanEntity planEntity);

}
