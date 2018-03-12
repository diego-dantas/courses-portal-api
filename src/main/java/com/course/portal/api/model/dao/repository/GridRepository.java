package com.course.portal.api.model.dao.repository;

import com.course.portal.api.model.dao.entity.GridEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GridRepository extends JpaRepository<GridEntity, Long>{


    @Query(value = "select g.*, sg.description from grid g  left join sub_grid sg on sg.grid__id = g._id where g._id = 2", nativeQuery = true)
    public List<GridEntity> getByGridSubGrid();

    @Query(value ="select * from grid where label_url =:labelUrl", nativeQuery = true)
    GridEntity findByLabelUrl(@Param("labelUrl") String labelUrl);
}
