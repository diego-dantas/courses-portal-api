package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<CourseEntity, Long>{


    @Query(value = "select * from course c where c.grid__id =:grid and c.sub_grid__id =:subGrid", nativeQuery = true)
    List<CourseEntity> findByGridAndSubGrid(@Param("grid") Long grid, @Param("subGrid") Long subGrid);


}
